package com.cqgk.clerk.helper;

import com.cqgk.clerk.base.Basic;
import com.cqgk.clerk.view.LoadingProgressView;

/**
 * loading框
 *
 * @author duke
 */
public class ProgressDialogHelper extends Basic {

    private static ProgressDialogHelper mDialogHelper;

    private LoadingProgressView myProgressDialog;

    public static ProgressDialogHelper get() {
        if (mDialogHelper == null) {
            mDialogHelper = new ProgressDialogHelper();
        }
        return mDialogHelper;
    }

    public void destroy() {
        dismiss();
        myProgressDialog = null;
    }

    public void show() {
        show(-1);
    }

    public void show(int theme) {
        if (getActivity() == null) {
            return;
        }

        if (myProgressDialog != null && myProgressDialog.getContext() != getActivity()) {
            myProgressDialog.cancel();
            myProgressDialog = null;
        }

        if (myProgressDialog == null) {
            if (theme != 0) {
                myProgressDialog = new LoadingProgressView(getActivity(), theme);
            } else {
                myProgressDialog = new LoadingProgressView(getActivity());
            }
        }

        if (getActivity() != null && !getActivity().isFinishing())
            myProgressDialog.show();

    }

    public void dismiss() {
        if (myProgressDialog != null) {
            myProgressDialog.dismiss();
        }
    }
}
