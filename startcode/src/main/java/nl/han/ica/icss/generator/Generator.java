package nl.han.ica.icss.generator;


import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.selectors.TagSelector;

public class Generator {

	public String generate(AST ast) {
        return generateStylesheet(ast.root);
	}

	private String generateStylesheet(Stylesheet node){
		for(ASTNode child : node.getChildren()) {
			if(child instanceof Stylerule) {
				return generateStylerule((Stylerule) child);
			}
		}
		return "SOMETHING WENT WRONG IN STYLESHEET";
	}

	private String generateStylerule(Stylerule node) { // moet nog uitgebreid worden is momenteel maar een
		for(ASTNode child : node.getChildren()) {
			return node.selectors.get(0).toString()
					+ " {\n"
					+ generateDeclaration((Declaration) child)
					+ "}";
		}
        return "SOMETHING WENT WRONG IN STYLERULE";
    }

	private String generateDeclaration(Declaration node) {
		return "SOMETHING WENT WRONG IN DECLARATION";
	}
}
