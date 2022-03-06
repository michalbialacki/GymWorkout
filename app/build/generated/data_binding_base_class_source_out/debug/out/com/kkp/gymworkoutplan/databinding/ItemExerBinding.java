// Generated by view binder compiler. Do not edit!
package com.kkp.gymworkoutplan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.kkp.gymworkoutplan.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemExerBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CheckBox cbDone;

  @NonNull
  public final ProgressBar pbSeriesDone;

  @NonNull
  public final TextView tvExName;

  private ItemExerBinding(@NonNull ConstraintLayout rootView, @NonNull CheckBox cbDone,
      @NonNull ProgressBar pbSeriesDone, @NonNull TextView tvExName) {
    this.rootView = rootView;
    this.cbDone = cbDone;
    this.pbSeriesDone = pbSeriesDone;
    this.tvExName = tvExName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemExerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemExerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_exer, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemExerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cbDone;
      CheckBox cbDone = ViewBindings.findChildViewById(rootView, id);
      if (cbDone == null) {
        break missingId;
      }

      id = R.id.pbSeriesDone;
      ProgressBar pbSeriesDone = ViewBindings.findChildViewById(rootView, id);
      if (pbSeriesDone == null) {
        break missingId;
      }

      id = R.id.tvExName;
      TextView tvExName = ViewBindings.findChildViewById(rootView, id);
      if (tvExName == null) {
        break missingId;
      }

      return new ItemExerBinding((ConstraintLayout) rootView, cbDone, pbSeriesDone, tvExName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}