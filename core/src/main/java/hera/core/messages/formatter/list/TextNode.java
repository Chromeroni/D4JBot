package hera.core.messages.formatter.list;

public class TextNode extends hera.core.messages.formatter.list.ListFormatNode {
	String value;

	public TextNode(String value)
	{
		super(false, false);
		this.value = value;
	}


	@Override
	protected String makeOutput(String... input) {
		return value;
	}
}
