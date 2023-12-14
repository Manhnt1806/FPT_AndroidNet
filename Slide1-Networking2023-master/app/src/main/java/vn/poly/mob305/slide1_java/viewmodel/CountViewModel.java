package vn.poly.mob305.slide1_java.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CountViewModel extends ViewModel {
    private MutableLiveData<Integer> clickCountLiveData
            = new MutableLiveData<>();
    private int clickCount = 0;

    public MutableLiveData<Integer> getClickCountLiveData() {
        if (clickCountLiveData == null) {
            clickCountLiveData = new MutableLiveData<>();
        }
        return clickCountLiveData;
    }

    public void incrementClickCount() {
        clickCount++;
        clickCountLiveData.setValue(clickCount);
    }
}
