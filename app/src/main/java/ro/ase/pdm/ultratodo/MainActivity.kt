package ro.ase.pdm.ultratodo

import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ro.ase.pdm.ultratodo.data.Todo
import ro.ase.pdm.ultratodo.data.TodoPriority
import ro.ase.pdm.ultratodo.data.TodoState
import ro.ase.pdm.ultratodo.data.TodoType
import ro.ase.pdm.ultratodo.databinding.ActivityMainBinding
import ro.ase.pdm.ultratodo.ui.todolist.TodoListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var todoListRecyclerView: RecyclerView
    private lateinit var todoListAdapter: TodoListAdapter
    private lateinit var todoList: List<Todo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_todo_list
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.appBarMain.fab.setOnClickListener {
//            navController.navigate(R.id.nav_new_todo)
        }

        //
        todoList = listOf(
            Todo(
                "test1",
                TodoPriority.Low,
                TodoState.Pending,
                TodoType.Normal,
                "lorem",
                null
            ),
            Todo(
                "test2",
                TodoPriority.Low,
                TodoState.Pending,
                TodoType.Normal,
                "lorem",
                null
            ), Todo(
                "test3",
                TodoPriority.Low,
                TodoState.Pending,
                TodoType.Normal,
                "lorem",
                null
            )
        )
        todoListRecyclerView = findViewById(R.id.todo_recycler_view)
        todoListAdapter = TodoListAdapter(todoList)
        todoListRecyclerView.adapter = todoListAdapter
        todoListRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}