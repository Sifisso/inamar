package mz.ciuem.inamar.comps;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import mz.ciuem.inamar.entity.Entity;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
 
public class DualListbox extends Div implements IdSpace {
     
   
	private static final long serialVersionUID = 4677944499755261814L;
	
	@Wire
    private Listbox candidateLb;
    @Wire
    private Listbox chosenLb;
 
    @SuppressWarnings("rawtypes")
	public ListModelList candidateModel;
    @SuppressWarnings("rawtypes")
	public ListModelList chosenDataModel;
 
    public DualListbox() {
        Executions.createComponents("/components/dual_listBox.zul", this, null);
        Selectors.wireComponents(this, this, false);
        Selectors.wireEventListeners(this, this);
        chosenLb.setModel(chosenDataModel = new ListModelList<>());
        chosenDataModel.setMultiple(true);
    }
 
    @SuppressWarnings("unchecked")
	@Listen("onClick = #chooseBtn")
    public void chooseItem() {
        Events.postEvent(new ChooseEvent(this, chooseOne()));
    }
 
    @SuppressWarnings("unchecked")
	@Listen("onClick = #removeBtn")
    public void unchooseItem() {
        Events.postEvent(new ChooseEvent(this, unchooseOne()));
    }
 
    @SuppressWarnings("unchecked")
	@Listen("onClick = #chooseAllBtn")
    public void chooseAllItem() {
        chosenDataModel.addAll(candidateModel);
        candidateModel.clear();
    }
 
    @SuppressWarnings("unchecked")
	@Listen("onClick = #removeAllBtn")
    public void unchooseAll() {
        candidateModel.addAll(chosenDataModel);
        chosenDataModel.clear();
    }
 
    @SuppressWarnings("unchecked")
	@Listen("onClick = #topBtn")
    public void top() {
        Set<Entity> selection = new LinkedHashSet<Entity>(chosenDataModel.getSelection());
        chosenDataModel.removeAll(selection);
        chosenDataModel.addAll(0, selection);
        chosenDataModel.setSelection(selection);
    }
 
    @SuppressWarnings("unchecked")
	@Listen("onClick = #upBtn")
    public void up() {
        Set<Entity> selection = new LinkedHashSet<Entity>(chosenDataModel.getSelection());
        if (selection.isEmpty())
            return;
        int index = chosenDataModel.indexOf(selection.iterator().next());
        if (index == 0 || index < 0)
            return;
        chosenDataModel.removeAll(selection);
        chosenDataModel.addAll(--index, selection);
        chosenDataModel.setSelection(selection);
 
    }
 
    @SuppressWarnings("unchecked")
	@Listen("onClick = #downBtn")
    public void down() {
		Set<Entity> selection = new LinkedHashSet<Entity>(chosenDataModel.getSelection());
        if (selection.isEmpty())
            return;
        int index = chosenDataModel.indexOf(selection.iterator().next());
        if (index == chosenDataModel.size() - 1 || index < 0)
            return;
        chosenDataModel.removeAll(selection);
        chosenDataModel.addAll(++index, selection);
        chosenDataModel.setSelection(selection);
    }
 
    @SuppressWarnings("unchecked")
	@Listen("onClick = #bottomBtn")
    public void bottom() {
        Set<Entity> selection = new LinkedHashSet<Entity>(chosenDataModel.getSelection());
        chosenDataModel.removeAll(selection);
        chosenDataModel.addAll(selection);
        chosenDataModel.setSelection(selection);
    }
 
    /**
     * Set new candidate ListModelList.
     * 
     * @param candidate
     *            is the data of candidate list model
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void setModel(List candidate) {
        candidateLb.setModel(this.candidateModel = new ListModelList<Entity>(candidate));
        this.candidateModel.setMultiple(true);
        chosenDataModel.clear();
    }
 
    /**
     * @return current chosen data list
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List getChosenDataList() {
        return new ArrayList<Entity>(chosenDataModel);
    }
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private Set chooseOne() {
        Set<Entity> set = candidateModel.getSelection();
        chosenDataModel.addAll(set);
        candidateModel.removeAll(set);
        return set;
    }
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private Set unchooseOne() {
        Set<Entity> set = chosenDataModel.getSelection();
        candidateModel.addAll(set);
        chosenDataModel.removeAll(set);
        return set;
    }
 
    // Customized Event
    public class ChooseEvent extends Event {
        private static final long serialVersionUID = -7334906383953342976L;
 
        public ChooseEvent(Component target, Set<Entity> data) {
            super("onChoose", target, data);
        }
    }
}
