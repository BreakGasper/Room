@file:OptIn(DelicateCoroutinesApi::class)

package com.breakapp.room

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.breakapp.room.databinding.ActivityMainBinding
import com.breakapp.room.room.TaskEntity
import com.breakapp.room.Repository.TaskRepoImpl
import com.breakapp.room.adapters.RickMortyAdapter
import com.breakapp.room.adapters.TaskAdapter
import com.breakapp.room.adapters.pokemonAdapter
import com.breakapp.room.retrofit.Pokemon
import com.breakapp.room.retrofit.RickMorty
import com.breakapp.room.viewmodel.TaskViewModel
import com.breakapp.room.viewmodel.TaskViewModelFactory
import com.breakapp.room.viewmodel.model.DataSource
import com.breakapp.room.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
@AndroidEntryPoint
class MainActivity : AppCompatActivity() , pokemonAdapter.OnTragoClickListener {
    lateinit var tasks: List<TaskEntity>
    private lateinit var b: ActivityMainBinding
    lateinit var adapter: TaskAdapter
    lateinit var adapterRM: RickMortyAdapter
    lateinit var adapterpokemon : pokemonAdapter
    private val vm by viewModels<TaskViewModel> ()

    private fun setUpObserverRickMorty(){
        vm.fetchRickList.observe(this,{
            res->
            println("RickMorty: " + res)

            when (res) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    Log.d("LIST: ", "${res.data}")
                    setupRM(res.data)
                }
                is Resource.Failure -> {

                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        tasks = ArrayList()
        //setUpObserverRickMorty()
        setupPokemon()


        b.btnRetro.setOnClickListener{
            setupPokemon()
        }
        b.btnRoom.setOnClickListener{
            setUpObservers()
        }
        b.btnAddTask.setOnClickListener {

            vm.guardarTrago(TaskEntity(0, b.etTask.text.toString()))
            //adapter.notifyItemInserted(tasks.size)
            setUpObservers()
            clearFocus()
            hideKeyboard()
        }
    }

    private fun setupPokemon() {
        vm.fetchPokemon.observe(this,{
            res->
        println("Pokemon: " + res)

        when (res) {
            is Resource.Loading -> {

            }
            is Resource.Success -> {
                Log.d("LIST: ", "${res.data}")
                setUpPokemonrv(res.data)
            }
            is Resource.Failure -> {

            }
        }
    })
    }

    private fun setUpObservers() {

        vm.getTasks().observe(this, { res ->
            when (res) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    Log.d("LIST: ", "${res.data}")

                    setUpRecyclerView(res.data)
                }
                is Resource.Failure -> {

                }
            }
        })
    }

   /* fun room() {
        tasks = ArrayList()
        getTasks()
        b.btnAddTask.setText("save")
        b.btnAddTask.setOnClickListener {
            addTask(TaskEntity(name = b.etTask.text.toString()))
        }
    }*/

    fun clearFocus() {
        b.etTask.setText("")
    }

    fun Context.hideKeyboard() {
        val inputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }

    /*fun addTask(task: TaskEntity) {
        GlobalScope.async(Dispatchers.Default) {
            //do background work

//            val id = MisNotesApp.database.taskDao().addTask(task)
//            val recoveryTask = MisNotesApp.database.taskDao().getTaskById(id)

            withContext(Main) {
                //do ui work
//                tasks.add(recoveryTask)
                adapter.notifyItemInserted(tasks.size)
                clearFocus()
                hideKeyboard()
            }
        }

    }*/

    /*fun getTasks() {
        GlobalScope.async(Dispatchers.Default) {
            //do background work
//            tasks = MisNotesApp.database.taskDao().getAllTask()
            withContext(Main) {
                //do ui work
//                setUpRecyclerView(tasks)
            }
        }
    }*/

    fun setUpPokemonrv(pk: List<Pokemon>) {
        adapterpokemon = pokemonAdapter(this ,pk,this)


        b.rvTask.setHasFixedSize(true)
        b.rvTask.layoutManager = LinearLayoutManager(this)
        b.rvTask.adapter = adapterpokemon
    }

    fun setUpRecyclerView(tasks: List<TaskEntity>) {
        adapter = TaskAdapter(tasks, { updateTask(it) }) { deleteTask(it) }


        b.rvTask.setHasFixedSize(true)
        b.rvTask.layoutManager = LinearLayoutManager(this)
        b.rvTask.adapter = adapter
    }

    fun setupRM(rm: List<RickMorty>){
//        adapterRM = RickMortyAdapter(this,rm,this)
        b.rvTask.setHasFixedSize(true)
        b.rvTask.layoutManager = LinearLayoutManager(this)
        b.rvTask.adapter = adapterRM

    }

    fun updateTask(task: TaskEntity) {
//        task.isDone = !task.isDone
        vm.updateTask( TaskEntity(task.id, task.name, true))
        setUpObservers()
//        GlobalScope.async(Dispatchers.Default) {
//            //do background work
//
//            task.isDone = !task.isDone
////            MisNotesApp.database.taskDao().updateTask(task)
//
////            MisNotesApp.database.taskDao().updateTask(user)
//
//            getTasks()
//        }
    }

    fun deleteTask(task: TaskEntity) {
        vm.deleteTask(task)
        setUpObservers()
//        GlobalScope.async(Dispatchers.Default) {
//            //do background work
//            val position = tasks.indexOf(task)
////            MisNotesApp.database.taskDao().deleteTask(task)
////            tasks.remove(task)
//            withContext(Main) {
//                //do ui work
//                adapter.notifyItemRemoved(position)
//            }
//        }


    }



    override fun onTragoClick(drink: Pokemon, position: Int) {
        vm.guardarTrago(TaskEntity(0, drink.name))
    }


}