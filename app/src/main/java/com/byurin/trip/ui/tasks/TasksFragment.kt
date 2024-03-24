package com.byurin.trip.ui.tasks

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.byurin.trip.R
import com.byurin.trip.data.OptionMenu
import com.byurin.trip.data.Task
import com.byurin.trip.databinding.FragmentTasksBinding
import com.byurin.trip.util.onQueryTextChanged
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_tasks), TasksAdapter.OnItemClickListener {

    private val viewModel: TasksViewModel by viewModels()
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_tasks, menu)
        super.onCreateOptionsMenu(menu, inflater)

        Log.d("TasksFragment", "Attempting to access preferences on IO thread")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_edit -> {
                // task 수정
                viewModel.onOptionMenuSelected(OptionMenu.EDIT)
                true
            }

            R.id.action_delete -> {
                // task 삭제
                viewModel.onOptionMenuSelected(OptionMenu.DELETE)
                true
            }

            R.id.action_share -> {
                // task 공유
                viewModel.onOptionMenuSelected(OptionMenu.SHARE)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTasksBinding.bind(view)

        // TaskFragment 생성 시, Appbar 텍스트를 "일정으로 설정
        requireActivity().title = "일정"

        // ViewModel 인스턴스 초기화
        val tasksViewModel = ViewModelProvider(this).get(TasksViewModel::class.java)

        // FloatingAction Button 클릭 시 AddTasksActivity로 이동
        binding.addTaskFab.setOnClickListener {
            startActivity(Intent(requireContext(), AddTasksActivity::class.java))
        }


        // 메뉴 클릭 이벤트 처리
//        tasksViewModel.preferenceFlow.asLiveData().observe(viewLifecycleOwner) { preferences ->
//            when (preferences.optionMenu) {
//                OptionMenu.EDIT -> {
//                    Log.d("TasksFragment", "Edit menu selected")
//                }
//                OptionMenu.DELETE -> {
//                    Log.d("TasksFragment", "Delete menu selected")
//                }
//                OptionMenu.SHARE -> {
//                    Log.d("TasksFragment", "Share menu selected")
//                }
//            }
//        }

//        val binding = FragmentTasksBinding.bind(view)
//
//        val tasksAdapter = TasksAdapter(this)
//        binding.apply {
//            tasksRv.apply {
//                adapter = tasksAdapter
//                layoutManager = LinearLayoutManager(requireContext())
//                setHasFixedSize(true)
//            }
//                ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
//                    0,
//                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//                ) {
//                    override fun onMove(
//                        recyclerView: RecyclerView,
//                        viewHolder: RecyclerView.ViewHolder,
//                        target: RecyclerView.ViewHolder
//                    ): Boolean {
//                        return false
//                    }
//
//                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                        val task = tasksAdapter.currentList[viewHolder.adapterPosition]
//                        viewModel.onTaskSwiped(task)
//                    }
//                }).attachToRecyclerView(tasksRv)
//            }
//
//            viewModel.tasks.observe(viewLifecycleOwner) {
//                tasksAdapter.submitList(it)
//            }
//            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//                viewModel.tasksEvent.collect { event ->
//                    when (event) {
//                        is TasksViewModel.TasksEvent.ShowUndoDeleteTaskMessage -> {
//                            Snackbar.make(requireView(), "Tasks deleted", Snackbar.LENGTH_LONG)
//                                .setAction("UNDO") {
//                                    viewModel.onUndoDeleteClick(event.task)
//                                }.show()
//                        }
//
//                        else -> {}
//                    }
//                }
//            }
//
//            setHasOptionsMenu(true)
    }

    override fun onItemClick(task: Task) {
//            viewModel.onTaskSelected(task)
    }
}


