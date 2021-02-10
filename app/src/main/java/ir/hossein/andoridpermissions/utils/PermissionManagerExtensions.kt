package ir.hossein.andoridpermissions.utils

import ir.hossein.andoridpermissions.permissions.Permission
import ir.hossein.andoridpermissions.permissions.PermissionManager

fun PermissionManager.requestPermission(
    permissions: List<Permission>,
    rationaleMessage: String,
    action: (bool: Boolean) -> Unit
) = this.request(permissions).rationale(rationaleMessage).checkPermission { action(it) }

fun PermissionManager.requestPermissionWithDetails(
    permissions: List<Permission>,
    rationaleMessage: String,
    action: (map: Map<Permission, Boolean>) -> Unit
) = this.request(permissions).rationale(rationaleMessage).checkDetailedPermission { action(it) }