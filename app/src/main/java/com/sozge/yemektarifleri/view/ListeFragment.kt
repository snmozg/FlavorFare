package com.sozge.yemektarifleri.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.sozge.yemektarifleri.adapter.TarifAdapter
import com.sozge.yemektarifleri.databinding.FragmentListeBinding
import com.sozge.yemektarifleri.model.Tarif
import com.sozge.yemektarifleri.roomdb.TarifDAO
import com.sozge.yemektarifleri.roomdb.TarifDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ListeFragment : Fragment() {
    private var _binding: FragmentListeBinding? = null
    private val binding get() = _binding!!

    private val mDisposable = CompositeDisposable()
    private lateinit var db : TarifDatabase
    private lateinit var tarifDao: TarifDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(requireContext(), TarifDatabase::class.java, "Tarifler").build()
        tarifDao = db.tarifDao()
    }

    private fun handleResponse(tarifler: List<Tarif>) {
        val tarifAdapter = TarifAdapter(tarifler)
        binding.tarifRecyclerView.adapter = tarifAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener {yeniEkle(it)}
        binding.tarifRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        verileriAl()
    }

    private fun verileriAl() {
        mDisposable.add(
            tarifDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))
    }

    fun yeniEkle(view : View) {
        val action = ListeFragmentDirections.actionListeFragmentToTarifFragment(bilgi="yeni",id=0)
        Navigation.findNavController(view).navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mDisposable.clear()
    }
}