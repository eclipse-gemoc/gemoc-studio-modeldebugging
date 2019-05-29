package org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.ui.tools.api.project.ViewpointSpecificationProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class NewODesignFileWizardPage extends WizardPage {
	private static final String DOT = ".";

	private List<String> encodings;

	private Combo encodingField;

	private ModifyListener validator = new ModifyListener() {
		public void modifyText(final ModifyEvent e) {
			setPageComplete(validatePage());
			isVsmNameChanged = true;
		}
	};

	private Text modelName;

	// Check if VSM name has been modified
	private Boolean isVsmNameChanged = false;

	private NewGemocSiriusProjectWizardFields context;

	public NewODesignFileWizardPage(final String pageName,
			NewGemocSiriusProjectWizardFields context) {
		super(pageName);
		this.context = context;
	}

	public Text getModelName() {
		return modelName;
	}

	public String getEncoding() {
		return encodingField.getText();
	}

	public String getInitialObjectName() {
		return ViewpointSpecificationProject.INITIAL_OBJECT_NAME;
	}

	public void createControl(final Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		final GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.verticalSpacing = 12;
		composite.setLayout(layout);

		GridData data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.grabExcessVerticalSpace = true;
		data.horizontalAlignment = GridData.FILL;
		composite.setLayoutData(data);

		final Label modelNameLabel = new Label(composite, SWT.LEFT);
		modelNameLabel.setText(SiriusEditorPlugin.getPlugin().getString(
				"_UI_SiriusModelWizardName_label"));

		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		modelNameLabel.setLayoutData(data);

		modelName = new Text(composite, SWT.LEFT | SWT.BORDER);
		modelName.setText(extractModelName(context.projectName));

		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		modelName.setLayoutData(data);
		modelName.addModifyListener(validator);

		final Label encodingLabel = new Label(composite, SWT.LEFT);
		encodingLabel.setText(SiriusEditorPlugin.getPlugin().getString(
				"_UI_XMLEncoding"));

		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		encodingLabel.setLayoutData(data);

		encodingField = new Combo(composite, SWT.BORDER);
		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		encodingField.setLayoutData(data);

		for (final String string : getEncodings()) {
			encodingField.add(string);
		}

		encodingField.select(0);
		encodingField.addModifyListener(validator);

		setPageComplete(validatePage());
		setControl(composite);
	}

	protected boolean validatePage() {
		return /* getInitialObjectName() != null && */getEncodings()
				.contains(encodingField.getText())
				&& getModelName()
						.getText()
						.endsWith(
								DOT
										+ ViewpointSpecificationProject.VIEWPOINT_MODEL_EXTENSION)
				&& (getModelName().getText().length() > (ViewpointSpecificationProject.VIEWPOINT_MODEL_EXTENSION
						.length() + 1));
	}

	private Collection<String> getEncodings() {
		if (encodings == null) {
			encodings = new ArrayList<String>();
			final StringTokenizer stringTokenizer = new StringTokenizer(
					SiriusEditorPlugin.getPlugin().getString(
							"_UI_XMLEncodingChoices"));
			while (stringTokenizer.hasMoreTokens()) {
				encodings.add(stringTokenizer.nextToken());
			}
		}
		return encodings;
	}

	public void setVisible(boolean visible) {
		if (visible) {
			if (!isVsmNameChanged) {
				this.modelName.setText(extractModelName(context.projectName));
			}
		}
		super.setVisible(visible);
	}

	private String extractModelName(String projectName) {
		String modelPrefixName = "";
		if (projectName != null && projectName.contains(".")) {
			String[] projectNames = projectName.split("[.]");
			if ("design".equals(projectNames[projectNames.length - 1])) {
				modelPrefixName = projectNames[projectNames.length - 2];
			} else {
				modelPrefixName = projectNames[projectNames.length - 1];
			}
		} else {
			modelPrefixName = projectName;
		}
		return modelPrefixName + DOT
				+ ViewpointSpecificationProject.VIEWPOINT_MODEL_EXTENSION;
	}
}
