package com.activity.studentregistercrud

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.activity.studentregistercrud.DB.Student
import com.activity.studentregistercrud.databinding.ListItemBinding

//provide the viewHolder as the type of adapter and implement override methods
//copy the clickListener parameter and set in recycleAdapter Class
class StudentRecycleViewAdapter(
    private val clickListener: (Student) -> Unit):RecyclerView.Adapter<StudentViewHolder>(){
    private val studentList = ArrayList<Student>()
    private lateinit var binding: ListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
       /* //create layout inflater
        val layoutInflater = LayoutInflater.from(parent.context)
        //inflate the list_item
        val listItem = layoutInflater.inflate(R.layout.list_item,parent,false)
        //create instance of viewHolder class and pass the list_item instance and return
        return StudentViewHolder(listItem)*/
        //viewBinding
        val listBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StudentViewHolder(listBinding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        //get the relevant student object from the list using the position value
        holder.bind(studentList[position],clickListener)
    }

    override fun getItemCount(): Int {
        //return the size of the list
        return studentList.size
    }

    //setter function to list
    fun setList(students : List<Student>){
        studentList.clear()
        studentList.addAll(students)
    }

}

/*create ViewHolder Class for the list_item.xml and provide as a type
of the recycleView adapter.
view as constructor parameter and extend recycleView Holder Class*/
class StudentViewHolder(private val listItemBinding: ListItemBinding):RecyclerView.ViewHolder(listItemBinding.root){
    //function to bind values to list_item layout and add student instance as parameter
    fun bind(student: Student, clickListener: (Student) -> Unit){
        listItemBinding.apply {
           /* //get the view of list_item
            val tvName = view.findViewById<TextView>(R.id.tvName)
            val tvEmail = view.findViewById<TextView>(R.id.tvEmail)*/
            //get name and email value from the student instance and assign it to the TextView
            tvName.text = student.name
            tvEmail.text = student.email
        }


        //implement setOnclickListener to the list_item
        listItemBinding.root.setOnClickListener {
            clickListener(student)
        }
    }
}