package nl.han.ica.icss.parser;

import java.util.Stack;


import nl.han.ica.datastructures.HANStack;
import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;
import org.checkerframework.checker.units.qual.C;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {
	
	//Accumulator attributes:
	private AST ast;

	//Use this to keep track of the parent nodes when recursively traversing the ast
	private IHANStack<ASTNode> currentContainer;

	public ASTListener() {
		ast = new AST();
		currentContainer = new HANStack<>();
	}
    public AST getAST() {
        return ast;
    }

	@Override
	public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
		Stylesheet stylesheet = new Stylesheet();
		currentContainer.push(stylesheet);
	}

	@Override
	public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
		Stylesheet stylesheet = (Stylesheet) currentContainer.pop();
		ast.setRoot(stylesheet);
	}

	@Override
	public void enterStylerule(ICSSParser.StyleruleContext ctx) {
		Stylerule stylerule = new Stylerule();
		currentContainer.push(stylerule);
	}

	@Override
	public void exitStylerule(ICSSParser.StyleruleContext ctx) {
		Stylerule stylerule = (Stylerule) currentContainer.pop();
		currentContainer.peek().addChild(stylerule);
	}

	@Override
	public void enterId_selector(ICSSParser.Id_selectorContext ctx) {
		IdSelector selector = new IdSelector(ctx.getText());
		currentContainer.push(selector);
	}

	@Override
	public void exitId_selector(ICSSParser.Id_selectorContext ctx) {
		IdSelector selector = (IdSelector) currentContainer.pop();
		currentContainer.peek().addChild(selector);
	}

	@Override
	public void enterClass_selector(ICSSParser.Class_selectorContext ctx) {
		ClassSelector selector = new ClassSelector(ctx.getText());
		currentContainer.push(selector);
	}

	@Override
	public void exitClass_selector(ICSSParser.Class_selectorContext ctx) {
		ClassSelector selector = (ClassSelector) currentContainer.pop();
		currentContainer.peek().addChild(selector);
	}

	@Override
	public void enterTag_selector(ICSSParser.Tag_selectorContext ctx) {
		TagSelector selector = new TagSelector(ctx.getText());
		currentContainer.push(selector);
	}

	@Override
	public void exitTag_selector(ICSSParser.Tag_selectorContext ctx) {
		TagSelector selector = (TagSelector) currentContainer.pop();
		currentContainer.peek().addChild(selector);
	}

//	@Override
//	public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
//		Declaration declaration = new Declaration();
//		currentContainer.push(declaration);
//	}
//
//	@Override
//	public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
//		Declaration declaration = (Declaration) currentContainer.pop();
//		currentContainer.peek().addChild(declaration);
//	}

	@Override
	public void enterColor_declaration(ICSSParser.Color_declarationContext ctx) {
		Declaration declaration = new Declaration(ctx.getText());
		currentContainer.push(declaration);
	}

	@Override
	public void exitColor_declaration(ICSSParser.Color_declarationContext ctx) {
		Declaration declaration = (Declaration) currentContainer.pop();
		currentContainer.peek().addChild(declaration);
	}

	@Override
	public void enterSize_declaration(ICSSParser.Size_declarationContext ctx) {
		Declaration declaration = new Declaration(ctx.getText());
		currentContainer.push(declaration);
	}

	@Override
	public void exitSize_declaration(ICSSParser.Size_declarationContext ctx) {
		Declaration declaration = (Declaration) currentContainer.pop();
		currentContainer.peek().addChild(declaration);
	}

	@Override
	public void enterScalar_literal(ICSSParser.Scalar_literalContext ctx) {
		ScalarLiteral literal = new ScalarLiteral(ctx.getText());
		currentContainer.push(literal);
	}

	@Override
	public void exitScalar_literal(ICSSParser.Scalar_literalContext ctx) {
		ScalarLiteral literal = (ScalarLiteral) currentContainer.pop();
		currentContainer.peek().addChild(literal);
	}

	@Override
	public void enterPercentage_literal(ICSSParser.Percentage_literalContext ctx) {
		PercentageLiteral literal = new PercentageLiteral(ctx.getText());
		currentContainer.push(literal);
	}

	@Override
	public void exitPercentage_literal(ICSSParser.Percentage_literalContext ctx) {
		PercentageLiteral literal = (PercentageLiteral) currentContainer.pop();
		currentContainer.peek().addChild(literal);
	}

	@Override
	public void enterPixel_literal(ICSSParser.Pixel_literalContext ctx) {
		PixelLiteral literal = new PixelLiteral(ctx.getText());
		currentContainer.push(literal);
	}

	@Override
	public void exitPixel_literal(ICSSParser.Pixel_literalContext ctx) {
		PixelLiteral literal = (PixelLiteral) currentContainer.pop();
		currentContainer.peek().addChild(literal);
	}

	@Override
	public void enterBool_literal(ICSSParser.Bool_literalContext ctx) {
		BoolLiteral literal = new BoolLiteral(ctx.getText());
		currentContainer.push(literal);
	}

	@Override
	public void exitBool_literal(ICSSParser.Bool_literalContext ctx) {
		BoolLiteral literal = (BoolLiteral) currentContainer.pop();
		currentContainer.peek().addChild(literal);
	}

	@Override
	public void enterColor_literal(ICSSParser.Color_literalContext ctx) {
		ColorLiteral literal = new ColorLiteral(ctx.getText());
		currentContainer.push(literal);
	}

	@Override
	public void exitColor_literal(ICSSParser.Color_literalContext ctx) {
		ColorLiteral literal = (ColorLiteral) currentContainer.pop();
		currentContainer.peek().addChild(literal);
	}

	@Override
	public void enterColor_property(ICSSParser.Color_propertyContext ctx) {
		PropertyName prop = new PropertyName(ctx.getText());
		currentContainer.push(prop);
	}

	@Override
	public void exitColor_property(ICSSParser.Color_propertyContext ctx) {
		PropertyName prop = (PropertyName) currentContainer.pop();
		currentContainer.peek().addChild(prop);
	}

	@Override
	public void enterSize_property(ICSSParser.Size_propertyContext ctx) {
		PropertyName prop = new PropertyName(ctx.getText());
		currentContainer.push(prop);
	}

	@Override
	public void exitSize_property(ICSSParser.Size_propertyContext ctx) {
		PropertyName prop = (PropertyName) currentContainer.pop();
		currentContainer.peek().addChild(prop);
	}
}