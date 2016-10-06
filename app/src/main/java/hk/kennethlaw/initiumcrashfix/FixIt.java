package hk.kennethlaw.initiumcrashfix;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by kennethlaw on 7/10/2016.
 */

public class FixIt implements IXposedHookLoadPackage {
	@Override
	public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
		if (loadPackageParam.packageName.equals("com.theinitium.news")) {
			ClassLoader classLoader = loadPackageParam.classLoader;
			Class<?> dClass = XposedHelpers.findClass("com.theinitium.news.d", classLoader);
			XposedHelpers.findAndHookMethod(dClass, "f", new MethodPatch());
		}
	}

	public static class MethodPatch extends XC_MethodHook {
		@Override
		protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
			param.setResult(false);
		}
	}
}
