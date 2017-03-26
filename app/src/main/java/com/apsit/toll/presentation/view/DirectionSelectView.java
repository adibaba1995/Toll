package com.apsit.toll.presentation.view;

import com.apsit.toll.data.network.pojo.autocomplete.Prediction;

import java.util.List;

/**
 * Created by adityathanekar on 23/03/17.
 */

public interface DirectionSelectView extends BaseView{
    void setAutocompleteList(List<Prediction> autocompleteData);
}
