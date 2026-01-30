package com.curo.compliance.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.curo.compliance.R
import com.curo.compliance.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val mobile = binding.etMobile.text.toString()
            
            if (mobile.isEmpty() || mobile.length != 10) {
                Toast.makeText(context, "Please enter a valid 10-digit mobile number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simulate sending OTP
            Toast.makeText(context, "OTP sent to $mobile", Toast.LENGTH_SHORT).show()

            // Navigate to OTP screen
            val bundle = Bundle()
            bundle.putString("phoneNumber", mobile)
            findNavController().navigate(R.id.action_login_to_otp, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
