package info.evison.dan.robotcontroller1.view;

import android.content.Context;
import android.view.View;
import info.evison.dan.robotcontroller1.model.*;

public class FieldViewFactory {

    // TODO: have better factory
    static public View createView(Context context, FieldModel model) {
        if (model instanceof BooleanFieldModel)
            return new BooleanFieldView(context, (BooleanFieldModel) model);
        if (model instanceof ChoiceFieldModel)
            return new ChoiceFieldView(context, (ChoiceFieldModel) model);
        if (model instanceof RangeFieldModel)
            return new RangeFieldView(context, (RangeFieldModel) model);
        throw new RuntimeException("Unrecognized FieldModel: " + model);
    }
}
