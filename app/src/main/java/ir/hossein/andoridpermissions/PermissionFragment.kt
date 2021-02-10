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
                .request(Permission.Camera)
                .rationale("I need this to shove it in your ass!")
                .checkPermission { isGranted ->
                    if (isGranted) {
                        Toast.makeText(requireContext(), "Shoved it", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Didn't shove it", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }
    }
}