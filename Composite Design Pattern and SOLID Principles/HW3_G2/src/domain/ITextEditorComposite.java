package domain;

public interface ITextEditorComposite extends ITextEditorComponent {
	
	public void addComponent(ITextEditorComponent component);
	
	public void removeComponent(ITextEditorComponent component);
	
	@SuppressWarnings("rawtypes")
	public ITextEditorComponent getInstanceOf(Class clazz);
	
	public ISearch getSearch();
	
}
