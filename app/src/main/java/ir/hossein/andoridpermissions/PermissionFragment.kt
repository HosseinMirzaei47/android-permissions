package ir.hossein.andoridpermissions

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.hossein.andoridpermissions.permissions.Permission
import ir.hossein.andoridpermissions.permissions.PermissionManager
import kotlinx.android.synthetic.main.fragment_permission.*

class PermissionFragment : Fragment(R.layout.fragment_permission) {

    private val permissionManager = PermissionManager.from(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        request.setOnClickListener {
            permissionManager
                .request(
                    Permission.Camera,
                    Permission.StorageRead
                )
                .rationale("This a rationale!")
                .checkDetailedPermission { map ->
                    if (map.all { it.value }) {
                        createToast("All granted")
                    } else {
                        for (permission in map) {
                            if (permission.value) {
                                createToast("Granted: ${permission.key}")
                            } else {
                                println("Not granted:: ${permission.key}")
                            }
                        }
                    }
                }
        }
    }

    private fun createToast(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }
}