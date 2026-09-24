package io.github.sadeghi.online_shop.data.repository

import android.content.ContentResolver
import androidx.core.net.toUri
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.storage
import io.github.sadeghi.online_shop.data.local.datastore.UserPreferences
import io.github.sadeghi.online_shop.data.remote.model.ProfileDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import io.ktor.http.ContentType

class ProfileRepository @Inject constructor(
    private val supabaseClient: SupabaseClient,
    private val contentResolver: ContentResolver
) : IProfileRepository
{

    override suspend fun saveProfileImage(uri: String) {

        val userId = supabaseClient.auth.currentUserOrNull()?.id
            ?: throw IllegalStateException("کاربر وارد نشده است")

        val imageUri = uri.toUri()

        val bytes = contentResolver
            .openInputStream(imageUri)
            ?.use { it.readBytes() }
            ?: throw IllegalStateException("امکان خواندن تصویر وجود ندارد")

        val path = "$userId/profile.jpg"

        supabaseClient
            .storage
            .from("avatars")
            .upload(
                path = path,
                data = bytes
            ) {
                upsert = true
                contentType = ContentType.Image.JPEG
            }

        val publicUrl = supabaseClient
            .storage
            .from("avatars")
            .publicUrl(path)

        val versionedUrl = "$publicUrl?v=${System.currentTimeMillis()}"

        supabaseClient
            .from("profiles")
            .update(
                mapOf(
                    "avatar_url" to versionedUrl
                )
            ) {
                filter {
                    eq("id", userId)
                }
            }
    }


    override suspend fun saveProfile(
        fullName: String,
        phoneNumber: String,
        birthDate: String,
        gender: String
    ) {
        val userId = supabaseClient.auth.currentUserOrNull()?.id
            ?: throw IllegalStateException("کاربر وارد نشده است")

        supabaseClient
            .from("profiles")
            .upsert(
                ProfileDto(
                    id = userId,
                    fullName = fullName,
                    phoneNumber = phoneNumber,
                    birthDate = birthDate.takeIf { it.isNotBlank() },
                    gender = gender.takeIf { it.isNotBlank() }
                )
            )
    }

    override fun getFullName(): Flow<String> = flow {
        val userId = supabaseClient.auth.currentUserOrNull()?.id
            ?: return@flow

        val profile = supabaseClient
            .from("profiles")
            .select {
                filter {
                    eq("id", userId)
                }
            }
            .decodeSingleOrNull<ProfileDto>()

        emit(profile?.fullName.orEmpty())
    }
    override suspend fun getFullNameByUserId(
        userId: String
    ): String {

        val profile =
            supabaseClient
                .from("profiles")
                .select {
                    filter {
                        eq("id", userId)
                    }
                }
                .decodeSingleOrNull<ProfileDto>()

        return profile?.fullName.orEmpty()
    }

    override fun getPhoneNumber(): Flow<String> = flow {
        val userId = supabaseClient.auth.currentUserOrNull()?.id
            ?: return@flow

        val profile = supabaseClient
            .from("profiles")
            .select {
                filter {
                    eq("id", userId)
                }
            }
            .decodeSingleOrNull<ProfileDto>()

        emit(profile?.phoneNumber.orEmpty())
    }

    override fun getEmail(): Flow<String?> {
        return flow {
            emit(
                supabaseClient.auth.currentUserOrNull()?.email
            )
        }
    }

    override fun getBirthDate(): Flow<String> = flow {
        val userId = supabaseClient.auth.currentUserOrNull()?.id
            ?: return@flow

        val profile = supabaseClient
            .from("profiles")
            .select {
                filter {
                    eq("id", userId)
                }
            }
            .decodeSingleOrNull<ProfileDto>()

        emit(profile?.birthDate.orEmpty())
    }

    override fun getGender(): Flow<String> = flow {
        val userId = supabaseClient.auth.currentUserOrNull()?.id
            ?: return@flow

        val profile = supabaseClient
            .from("profiles")
            .select {
                filter {
                    eq("id", userId)
                }
            }
            .decodeSingleOrNull<ProfileDto>()

        emit(profile?.gender.orEmpty())
    }
    override fun getProfileImage(): Flow<String?> = flow {

        val userId = supabaseClient.auth.currentUserOrNull()?.id
            ?: return@flow

        val profile = supabaseClient
            .from("profiles")
            .select {
                filter {
                    eq("id", userId)
                }
            }
            .decodeSingleOrNull<ProfileDto>()

        println("PROFILE AVATAR URL = ${profile?.avatarUrl}")

        emit(profile?.avatarUrl)
    }

}