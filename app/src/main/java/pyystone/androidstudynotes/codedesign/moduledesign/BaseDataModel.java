package pyystone.androidstudynotes.codedesign.moduledesign;

/**
 * Created by pyysotne on 2015/12/25.
 * email: pyystone@163.com
 * QQ: 862429936
 *  基础数据类
 */
public abstract class BaseDataModel {
    BaseDataModel() {
        initChildData();
    }
    protected abstract void initChildData();
    protected abstract int getChildCount();
    protected abstract Object getChildByPosition(int position);
    protected abstract int getChildType();
}
