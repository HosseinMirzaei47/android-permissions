package ir.hossein.andoridpermissions

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.hossein.andoridpermissions.permissions.Permission
import ir.hossein.andoridpermissions.permissions.PermissionManager
import ir.hossein.andoridpermissions.utils.requestPermission
import ir.hossein.andoridpermissions.utils.requestPermissionWithDetails
import kotlinx.android.synthetic.main.fragment_permission.*

class PermissionFragment : Fragment(R.layout.fragment_permission) {

    private val permissionManager = PermissionManager.from(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClicks()
    }

    private fun setOnClicks() {
        request.setOnClickListener {
            permissionManager.requestPermission(
                listOf(Permission.Camera),
                "Rationale Message!"
            ) { isGranted ->
                if (isGranted) {
                    createToast("Shoved it")
                } else {
                    createToast("Didn't shove it")
                }
            }
        }

        requestWithDetails.setOnClickListener {
            permissionManager.requestPermissionWithDetails(
                listOf(Permission.StorageRead, Permission.StorageWrite),
                "Rationale Message!"
            ) { map ->
                if (map.all { it.value }) {
                    createToast("All granted")
                } else {
                    for (permission in map) {
                        if (permission.value) {
                            createToast("Granted: ${permission.key}")
                        } else {
                            createToast("Not granted:: ${permission.key}")
                        }
                    }
                }
            }
        }
    }

    private fun createToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}