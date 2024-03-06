package com.example.eshopkenost.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eshopkenost.CourcePage;
import com.example.eshopkenost.R;
import com.example.eshopkenost.model.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{

    Context context;
    List<Course> course;

    public CourseAdapter(Context context, List<Course> course) {
        this.context = context;
        this.course = course;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseItem = LayoutInflater.from(context).inflate(R.layout.course_item, parent, false);
        return new CourseAdapter.CourseViewHolder(courseItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.courseBg.setCardBackgroundColor(Color.parseColor(course.get(position).getColor()));
        int imageId = context.getResources().getIdentifier(course.get(position).getImg(), "drawable", context.getOpPackageName());
        holder.courseImage.setImageResource(imageId);

        holder.courseTitle.setText(course.get(position).getTitle());
        holder.courseDate.setText(course.get(position).getDate());
        holder.courseLevel.setText(course.get(position).getLevel());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CourcePage.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, new Pair<View, String>(holder.courseImage, "courseImage"));

                intent.putExtra("courseBg", Color.parseColor(course.get(position).getColor()));
                intent.putExtra("courseImage", imageId);
                intent.putExtra("courseTitle", course.get(position).getTitle());
                intent.putExtra("courseDate", course.get(position).getDate());
                intent.putExtra("courseLevel", course.get(position).getLevel());
                intent.putExtra("courseText", course.get(position).getText());
                intent.putExtra("courseId", course.get(position).getId());


                context.startActivity(intent, options.toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return course.size();
    }

    public static final class CourseViewHolder extends RecyclerView.ViewHolder{

        CardView courseBg;
        ImageView courseImage;
        TextView courseTitle, courseDate, courseLevel;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            courseBg = itemView.findViewById(R.id.courseBg);
            courseImage = itemView.findViewById(R.id.courseImage);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            courseDate = itemView.findViewById(R.id.courseDate);
            courseLevel = itemView.findViewById(R.id.courseLevel);

        }
    }
}
