package org.eclipse.gemoc.executionframework.engine.headless;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionPlatform;
import org.eclipse.gemoc.xdsmlframework.api.core.IModelLoader;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.EngineAddonSortingRule.EngineEvent;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

public class HeadlessExecutionPlatform implements IExecutionPlatform {


	protected Collection<IEngineAddon> _addons = new ArrayList<IEngineAddon>();
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public IModelLoader getModelLoader() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object _addonLock = new Object();

	@Override
	public void addEngineAddon(IEngineAddon addon) {
		synchronized (_addonLock) {
			_addons.add(addon);
		}
	}

	@Override
	public void removeEngineAddon(IEngineAddon addon) {
		synchronized (_addonLock) {
			_addons.remove(addon);
		}
	}


	@Override
	public Iterable<IEngineAddon> getEngineAddons() {
		synchronized (_addonLock) {
			return Collections.unmodifiableCollection(new ArrayList<IEngineAddon>(_addons));
		}
	}

	@Override
	public List<IEngineAddon> getSortedEngineAddons(EngineEvent engineEvent) {
		synchronized (_addonLock) {
			return Collections.unmodifiableList(new ArrayList<IEngineAddon>(_addons));
		}
	}

}
