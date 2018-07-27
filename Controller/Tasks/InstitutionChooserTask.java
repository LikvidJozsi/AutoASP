package Controller.Tasks;

import java.util.List;

import DAL.InstitutionChooserMenu;
import DAL.KaszperPage;

public class InstitutionChooserTask extends Task {
	
	private String institutiontochoose;
	
	public InstitutionChooserTask() {}
	
	public InstitutionChooserTask(KaszperPage startingpage,String institutiontochoose) {
		super(startingpage);
		this.institutiontochoose = institutiontochoose;
	}
	
	public void setInstitutionToChoose(String name) {
		this.institutiontochoose = name;
	}

	@Override
	public KaszperPage execute() {
		InstitutionChooserMenu menu = new InstitutionChooserMenu(startingpage);
		
		List<String> names = menu.getInstitutions();
		for (int i = 0; i < names.size(); i++) {
			if(names.get(i).toLowerCase().contains(institutiontochoose.toLowerCase())) {
				menu.selectInstituiton(i);
				break;
			}
		}
		return menu;
	}

}
