package com.valentino.questionbank.instructor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.valentino.questionbank.R
import com.valentino.questionbank.api.ApiService
import com.valentino.questionbank.model.Course
import com.valentino.questionbank.model.Folder
import com.valentino.questionbank.utilities.defaultPrefs
import com.valentino.questionbank.utilities.session
import kotlinx.android.synthetic.main.activity_add_folder.*

private const val SUCCESS_CODE = 1
private const val COURSE_PARAM = "course"

class AddFolderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_folder)
        val course = intent.getParcelableExtra<Course>(COURSE_PARAM)
        submitButton.setOnClickListener({
            if (validate()) {
                Log.d("AddFolderActivity", "Validated")
                val newFolder = Folder(course.cid!!, nameTextView.text.toString(), descriptionTextView.text.toString())
                Log.d("AddFolderActivity", newFolder.toString())
                ApiService.postFolder(defaultPrefs(this).session, newFolder) {
                    finish()
                }
            }
        })
    }

    private fun validate() : Boolean {
        return (nameTextView.text.isNotBlank()
                && descriptionTextView.text.isNotBlank())
    }
}
