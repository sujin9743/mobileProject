package jsj.mjc.mobileproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private ArrayList<TodoItem> todoList;

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        CheckBox todoCheckBox;
        TextView todoTxt;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            todoCheckBox = itemView.findViewById(R.id.todo_checkBox);
            todoTxt = itemView.findViewById(R.id.todoTxt);
        }
    }

    //데이터 리스트 객체 전달
    TodoAdapter(ArrayList<TodoItem> todoList) {
        this.todoList = todoList;
    }

    //아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @NonNull
    @Override
    public TodoAdapter.TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_todo, parent, false);
        TodoViewHolder viewHolder = new TodoViewHolder(view);
        return viewHolder;
    }

    //position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.TodoViewHolder holder, int position) {
        holder.todoCheckBox.setChecked(false);
        holder.todoTxt.setText(todoList.get(position).getTodoTxt());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
