package org.eclipse.gemoc.executionframework.event.ui.views;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IResource;
import org.eclipse.debug.internal.ui.SWTFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gemoc.commons.eclipse.ui.dialogs.SelectAnyIFileDialog;
import org.eclipse.gemoc.executionframework.value.model.value.BooleanAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.BooleanObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.FloatAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.FloatObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.IntegerAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.IntegerObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.SingleObjectValue;
import org.eclipse.gemoc.executionframework.value.model.value.SingleReferenceValue;
import org.eclipse.gemoc.executionframework.value.model.value.StringAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.Value;
import org.eclipse.gemoc.executionframework.value.model.value.ValueFactory;
import org.eclipse.gemoc.executionframework.value.model.value.ValuePackage;
import org.eclipse.gemoc.executionframework.event.manager.IEventManager;
import org.eclipse.gemoc.executionframework.event.model.event.EventFactory;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceArgument;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceType;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.Event;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.EventParameter;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

@SuppressWarnings("restriction")
public class EventOccurrenceConfigurator {

	private final EventFactory eventFactory = EventFactory.eINSTANCE;
	private final ValueFactory valueFactory = ValueFactory.eINSTANCE;

	private final Map<EventParameter, EClass> paramToType;

	private final Map<EClass, List<EObject>> typeToElements;

	private final IEventManager eventManager;
	
	private EventOccurrence eventOccurrence = null;

	private final Function<Object, String> namingFunction;
	
	private final ResourceSet executedResourceSet;

	public EventOccurrenceConfigurator(Composite parent, IEventManager eventManager, Event event, Map<EventParameter, EClass> paramToType,
			Map<EClass, List<EObject>> typeToElements, Function<Object, String> namingFunction) {
		this.eventManager = eventManager;
		this.paramToType = paramToType;
		this.typeToElements = typeToElements;
		this.namingFunction = namingFunction;
		this.executedResourceSet = typeToElements.values().stream()
				.filter(l -> !l.isEmpty()).findAny()
				.map(l -> l.stream().findFirst()
						.map(v -> v.eResource().getResourceSet())
						.orElse(null))
				.orElse(null);
		final Group group = new Group(parent, SWT.NULL);
		final GridLayout configuratorLayout = new GridLayout();
		final GridData configuratorGridData = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		configuratorLayout.marginHeight = 10;
		configuratorLayout.marginWidth = 10;
		group.setText(event.getName());
		group.setLayout(configuratorLayout);
		group.setLayoutData(configuratorGridData);

		eventOccurrence = createEventOccurrence(event);
		
		if (!eventOccurrence.getArgs().isEmpty()) {
			final Group parameterGroup = new Group(group, SWT.NULL);
			final GridLayout parameterLayout = new GridLayout();
			final GridData parameterGridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
			parameterLayout.numColumns = 3;
			parameterLayout.marginHeight = 10;
			parameterLayout.marginWidth = 10;
			parameterGroup.setText("Parameters");
			parameterGroup.setLayout(parameterLayout);
			parameterGroup.setLayoutData(parameterGridData);

			eventOccurrence.getArgs().forEach(a -> {
				addParameterRow(parameterGroup, a.getParameter(), a);
			});
		}
		
		final Button sendButton = SWTFactory.createPushButton(group, "Send", null);
		sendButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		sendButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				EventOccurrenceConfigurator.this.eventManager.processEventOccurrence(copyEventOccurrence(eventOccurrence));
			}
		});

		group.requestLayout();
	}

	private EventOccurrence copyEventOccurrence(EventOccurrence eventOccurrence) {
		final EventOccurrence result = eventFactory.createEventOccurrence();
		result.setEvent(eventOccurrence.getEvent());
		result.setType(EventOccurrenceType.ACCEPTED);
		eventOccurrence.getArgs().stream().map(arg -> {
			final Value val = arg.getValue();
			final EventOccurrenceArgument newArg = eventFactory.createEventOccurrenceArgument();
			newArg.setParameter(arg.getParameter());
			final Value newVal = (Value) valueFactory.create(arg.getValue().eClass());
			newArg.setValue(newVal);
			switch(val.eClass().getClassifierID()) {
			case ValuePackage.BOOLEAN_ATTRIBUTE_VALUE:
				((BooleanAttributeValue) newVal).setAttributeValue(((BooleanAttributeValue) val).isAttributeValue());
				break;
			case ValuePackage.BOOLEAN_OBJECT_ATTRIBUTE_VALUE:
				((BooleanObjectAttributeValue) newVal).setAttributeValue(((BooleanObjectAttributeValue) val).getAttributeValue());
				break;
			case ValuePackage.INTEGER_ATTRIBUTE_VALUE:
				((IntegerAttributeValue) newVal).setAttributeValue(((IntegerAttributeValue) val).getAttributeValue());
				break;
			case ValuePackage.INTEGER_OBJECT_ATTRIBUTE_VALUE:
				((IntegerObjectAttributeValue) newVal).setAttributeValue(((IntegerObjectAttributeValue) val).getAttributeValue());
				break;
			case ValuePackage.FLOAT_ATTRIBUTE_VALUE:
				((FloatAttributeValue) newVal).setAttributeValue(((FloatAttributeValue) val).getAttributeValue());
				break;
			case ValuePackage.FLOAT_OBJECT_ATTRIBUTE_VALUE:
				((FloatObjectAttributeValue) newVal).setAttributeValue(((FloatObjectAttributeValue) val).getAttributeValue());
				break;
			case ValuePackage.STRING_ATTRIBUTE_VALUE:
				((StringAttributeValue) newVal).setAttributeValue(((StringAttributeValue) val).getAttributeValue());
				break;
			case ValuePackage.SINGLE_REFERENCE_VALUE:
				((SingleReferenceValue) newVal).setReferenceValue(((SingleReferenceValue) val).getReferenceValue());
				break;
			case ValuePackage.SINGLE_OBJECT_VALUE:
				((SingleObjectValue) newVal).setObjectValue(((SingleObjectValue) val).getObjectValue());
				break;
			}
			return newArg;
		}).forEach(arg -> result.getArgs().add(arg));
		
		return result;
	}
	
	private EventOccurrence createEventOccurrence(Event event) {
		final EventOccurrence eventOccurrence = eventFactory.createEventOccurrence();
		eventOccurrence.setEvent(event);
		event.getParams().forEach(p -> {
			eventOccurrence.getArgs().add(createEventOccurrenceArgument(p));
		});
		return eventOccurrence;
	}

	private EventOccurrenceArgument createEventOccurrenceArgument(EventParameter parameter) {
		final EventOccurrenceArgument argument = eventFactory.createEventOccurrenceArgument();
		argument.setParameter(parameter);
		createArgumentValue(argument);
		return argument;
	}
	
	private void createArgumentValue(EventOccurrenceArgument argument) {
		switch (argument.getParameter().getType()) {
		case "java.lang.Boolean": {
			final BooleanObjectAttributeValue val = ValueFactory.eINSTANCE.createBooleanObjectAttributeValue();
			val.setAttributeValue(false);
			argument.setValue(val);
		}
			break;
		case "boolean": {
			final BooleanAttributeValue val = ValueFactory.eINSTANCE.createBooleanAttributeValue();
			val.setAttributeValue(false);
			argument.setValue(val);
		}
			break;
		case "java.lang.Float": {
			final FloatObjectAttributeValue val = ValueFactory.eINSTANCE.createFloatObjectAttributeValue();
			val.setAttributeValue(0f);
			argument.setValue(val);
		}
			break;
		case "float": {
			final FloatAttributeValue val = ValueFactory.eINSTANCE.createFloatAttributeValue();
			val.setAttributeValue(0f);
			argument.setValue(val);
		}
			break;
		case "java.lang.Integer": {
			final IntegerObjectAttributeValue val = ValueFactory.eINSTANCE.createIntegerObjectAttributeValue();
			val.setAttributeValue(0);
			argument.setValue(val);
		}
			break;
		case "int": {
			final IntegerAttributeValue val = ValueFactory.eINSTANCE.createIntegerAttributeValue();
			val.setAttributeValue(0);
			argument.setValue(val);
		}
			break;
		case "java.lang.String": {
			final StringAttributeValue val = ValueFactory.eINSTANCE.createStringAttributeValue();
			val.setAttributeValue("");
			argument.setValue(val);
		}
			break;
		default: {
			final SingleReferenceValue val = ValueFactory.eINSTANCE.createSingleReferenceValue();
			argument.setValue(val);
		}
		}
	}

	private void addParameterRow(Composite parent, EventParameter parameter, EventOccurrenceArgument argument) {
		final Label parameterName = new Label(parent, SWT.NONE);
		parameterName.setText(parameter.getName());
		if (argument.getValue() instanceof SingleReferenceValue) {
			final EClass type = paramToType.get(parameter);
			addObjectParameterRow(parent, type, argument);
		} else {
			addPrimitiveParameterRow(parent, argument);
		}
	}

	private void addPrimitiveParameterRow(Composite parent, EventOccurrenceArgument argument) {
		final Text parameterValue = new Text(parent, SWT.SINGLE | SWT.BORDER);
		parameterValue.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		new Label(parent, SWT.NONE);
		parameterValue.addModifyListener(e -> {
			((StringAttributeValue) argument.getValue()).setAttributeValue(parameterValue.getText());
		});
	}
	
	private void addObjectParameterRow(Composite parent, EClass type, EventOccurrenceArgument argument) {
		final Combo parameterValue = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
		parameterValue.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		final Button parameterBrowseButton = SWTFactory.createPushButton(parent, "...", null);
		parameterBrowseButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		final List<EObject> possibleParameters = typeToElements.get(type);
		parameterValue.setItems(possibleParameters.stream().map(o -> namingFunction.apply(o))
				.collect(Collectors.toList()).toArray(new String[0]));
		parameterValue.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				final EObject selectedArgument = possibleParameters.get(parameterValue.getSelectionIndex());
				setArgumentReferenceValue(argument, selectedArgument);
			}
		});
		if (!possibleParameters.isEmpty()) {
			parameterValue.select(0);
			setArgumentReferenceValue(argument, possibleParameters.get(0));
		}
		parameterBrowseButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				final SelectAnyIFileDialog dialog = new SelectAnyIFileDialog();
				if (dialog.open() == Dialog.OK) {
					String modelPath = ((IResource) dialog.getResult()[0]).getFullPath().toPortableString();
					String uri = "platform:/resource" + modelPath;
					Resource resource = null;
					try {
						resource = executedResourceSet.getResource(URI.createURI(uri), true);
						EcoreUtil.resolveAll(resource);
						EObject root = resource.getContents().get(0);
						setArgumentObjectValue(argument, root);
						parameterValue.setText(namingFunction.apply(root));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	private void setArgumentReferenceValue(EventOccurrenceArgument argument, EObject value) {
		final SingleReferenceValue objectValue = valueFactory.createSingleReferenceValue();
		objectValue.setReferenceValue(value);
		argument.setValue(objectValue);
	}

	private void setArgumentObjectValue(EventOccurrenceArgument argument, EObject value) {
		final SingleObjectValue objectValue = valueFactory.createSingleObjectValue();
		final TransactionalEditingDomain ed = TransactionUtil.getEditingDomain(value.eResource());
		if (ed != null) {
			final RecordingCommand command = new RecordingCommand(ed, "") {
				protected void doExecute() {
					objectValue.setObjectValue(value);
					argument.setValue(objectValue);
				}
			};
			ed.getCommandStack().execute(command);
		}
	}

//	private void stuff(EventParameter parameter) {
//		final EClass type = paramToType.get(parameter);
//		if (type == null) {
//
//		} else {
//
//		}
//		if (type.equals(boolean.class)) {
//			final Button paramCheckBox = new Button(parent, SWT.CHECK);
//			paramCheckBox.setText(param.getName());
//			paramCheckBox.addSelectionListener(new SelectionAdapter() {
//				@Override
//				public void widgetSelected(SelectionEvent event) {
//					if (arg.getValue() instanceof BooleanAttributeValue) {
//						((BooleanAttributeValue) arg.getValue()).setAttributeValue(paramCheckBox.getSelection());
//					} else {
//						((BooleanObjectAttributeValue) arg.getValue()).setAttributeValue(paramCheckBox.getSelection());
//					}
//				}
//			});
//		} else {
//			final String name = param.getName() + ": " + type.getSimpleName();
//			createTextLabelLayout(parent, name);
//			if (type.isPrimitive() || type.equals(String.class)) {
//				final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
//				text.setLayoutData(createStandardLayout());
//				text.setEditable(true);
//				createTextLabelLayout(parent, "");
//
//				ModifyListener argModifyListener = new ModifyListener() {
//					@Override
//					public void modifyText(ModifyEvent e) {
//						// TODO
////						text.getText()
////						parameterToArgument.put(param, text.getText());
//					}
//				};
//
//				text.addModifyListener(argModifyListener);
//			} else {
//
//			}
//		}
//
//	}

//	private void loadEventOccurrenceParameters(Composite parent, EventOccurrence eventOccurrence) {
//		final Event event = eventOccurrence.getEvent();
//		final List<EventOccurrenceArgument> args = eventOccurrence.getArgs();
//		event.getParams().forEach(p -> {
//			EventOccurrenceArgument arg = args.stream().filter(a -> a.getParameter() == p).findFirst().orElse(null);
//			Class<?> type = getAttributeValueType(arg.getValue());
//			if (type == null) {
//				
//			} else {
//				createAttributeValue(arg);
//			}
//			addEventParameterRow(parent, p, type, arg);
//		});
//		final GridData gridData = createStandardLayout();
//		gridData.horizontalSpan = 3;
//		parent.setLayoutData(gridData);
//	}
}
