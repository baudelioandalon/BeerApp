package com.example.test.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.example.test.extensions.disableBackButton

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    lateinit var backHandler: BackHandler
    private var listenerBackPress: BackFragment? = null

    lateinit var binding: T

    /**
     * @author Baudelio Andalon
     * @see Obtener el layout
     */
    abstract fun getLayout(): Int
    open fun initDependency(savedInstanceState: Bundle? = null) {}
    open fun initObservers() {}
    abstract fun initView()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        if (activity is BackHandler) {
            backHandler = activity as BackHandler
        }
        initDependency(savedInstanceState)
        initObservers()
        initView()
        return binding.root
    }

    /**
     * @author DanielGC
     * @see Muestra el progressabar
     * @param message: String?
     * @param isCancelable: Boolean
     */
    fun showProgressBarCustom(message: String? = null, isCancelable: Boolean = false) {
        backHandler.showProgressBarCustom(message, isCancelable)
    }

    fun disableBackButton() {
        requireActivity().disableBackButton()
    }

    /**
     * @author DanielGC
     * @see Oculta el progressbar
     */
    fun hideProgressBarCustom() {
        backHandler.hideProgressBarCustom()
    }

    fun setBackFragment(listenerBackPress: BackFragment) {
        this.listenerBackPress = listenerBackPress
    }

    /**
     * @author DanielGC
     */
    fun hideKeyboard() {
        backHandler.hideKeyBoard()
    }

    fun showLottie(
        lottie: LottieAnimationView,
        containerPersonality: View,
        containerParent: View, show: Boolean
    ) {
        backHandler.showLottie(
            lottie,
            containerPersonality,
            containerParent, show
        )
    }

    /**
     * Acción que se ejecuta cuando se detecta que se preciona el boton de back de android,
     * esta funcion es llamada desde base activity
     */
    fun onFragmentBackPressed(): Boolean {
        listenerBackPress?.onBackPress()
        return false
    }

    /**
     * Interface que se implementa en cada fragmento que requiera aplicar una
     * funcionalidad cuando se detecte que se preciono el boton de back
     */
    interface BackFragment {
        fun onBackPress()
    }

}