package com.udistrital.syntax;
// Generated from CPP.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CPPParser}.
 */
public interface CPPListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CPPParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CPPParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CPPParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(CPPParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(CPPParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#type_specifier}.
	 * @param ctx the parse tree
	 */
	void enterType_specifier(CPPParser.Type_specifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#type_specifier}.
	 * @param ctx the parse tree
	 */
	void exitType_specifier(CPPParser.Type_specifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#declarator}.
	 * @param ctx the parse tree
	 */
	void enterDeclarator(CPPParser.DeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#declarator}.
	 * @param ctx the parse tree
	 */
	void exitDeclarator(CPPParser.DeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#function_definition}.
	 * @param ctx the parse tree
	 */
	void enterFunction_definition(CPPParser.Function_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#function_definition}.
	 * @param ctx the parse tree
	 */
	void exitFunction_definition(CPPParser.Function_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#parameter_list}.
	 * @param ctx the parse tree
	 */
	void enterParameter_list(CPPParser.Parameter_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#parameter_list}.
	 * @param ctx the parse tree
	 */
	void exitParameter_list(CPPParser.Parameter_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#compound_statement}.
	 * @param ctx the parse tree
	 */
	void enterCompound_statement(CPPParser.Compound_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#compound_statement}.
	 * @param ctx the parse tree
	 */
	void exitCompound_statement(CPPParser.Compound_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(CPPParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(CPPParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#expression_statement}.
	 * @param ctx the parse tree
	 */
	void enterExpression_statement(CPPParser.Expression_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#expression_statement}.
	 * @param ctx the parse tree
	 */
	void exitExpression_statement(CPPParser.Expression_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CPPParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CPPParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#assignment_expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_expression(CPPParser.Assignment_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#assignment_expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_expression(CPPParser.Assignment_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#additive_expression}.
	 * @param ctx the parse tree
	 */
	void enterAdditive_expression(CPPParser.Additive_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#additive_expression}.
	 * @param ctx the parse tree
	 */
	void exitAdditive_expression(CPPParser.Additive_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#multiplicative_expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicative_expression(CPPParser.Multiplicative_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#multiplicative_expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicative_expression(CPPParser.Multiplicative_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#unary_expression}.
	 * @param ctx the parse tree
	 */
	void enterUnary_expression(CPPParser.Unary_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#unary_expression}.
	 * @param ctx the parse tree
	 */
	void exitUnary_expression(CPPParser.Unary_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#primary_expression}.
	 * @param ctx the parse tree
	 */
	void enterPrimary_expression(CPPParser.Primary_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#primary_expression}.
	 * @param ctx the parse tree
	 */
	void exitPrimary_expression(CPPParser.Primary_expressionContext ctx);
}