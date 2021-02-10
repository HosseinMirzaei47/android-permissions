package ir.hossein.andoridpermissions.permissions

import android.Manifest.permission.*

sealed class Permission(vararg val permissions: String) {
    // Individual permissions
    object Camera : Permission(CAMERA)

    // Bundled permissions
    object MandatoryForFeatureOne : Permission(WRITE_EXTERNAL_STORAGE, ACCESS_FINE_LOCATION)

    // Grouped permissions
    object Location : Permission(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)
    object StorageRead : Permission(READ_EXTERNAL_STORAGE)
    object StorageWrite : Permission(WRITE_EXTERNAL_STORAGE)

    companion object {
        fun from(permission: String) = when (permission) {
            ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION -> Location
            WRITE_EXTERNAL_STORAGE -> StorageWrite
            READ_EXTERNAL_STORAGE -> StorageRead
            CAMERA -> Camera
            else -> throw IllegalArgumentException("Unknown permission: $permission")
        }
    }
}