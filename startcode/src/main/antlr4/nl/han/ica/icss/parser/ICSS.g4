grammar ICSS;

//--- LEXER: ---

// IF support:
IF: 'if';
ELSE: 'else';
BOX_BRACKET_OPEN: '[';
BOX_BRACKET_CLOSE: ']';


//Literals
TRUE: 'TRUE';
FALSE: 'FALSE';
PIXELSIZE: [0-9]+ 'px';
PERCENTAGE: [0-9]+ '%';
SCALAR: [0-9]+;


//Color value takes precedence over id idents
COLOR: '#' [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f];

//Specific identifiers for id's and css classes
ID_IDENT: '#' [a-z0-9\-]+;
CLASS_IDENT: '.' [a-z0-9\-]+;

//General identifiers
LOWER_IDENT: [a-z] [a-z0-9\-]*;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;

//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;

//
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COLON: ':';
PLUS: '+';
MIN: '-';
MUL: '*';
ASSIGNMENT_OPERATOR: ':=';

COLOR_PROPERTIES: 'color' COLON | 'background-color' COLON;
SIZE_PROPERTIES: 'width' COLON | 'height' COLON;


//--- PARSER: ---
stylesheet: (variable_assignment | stylerule)+;

variable_assignment: variable_reference ASSIGNMENT_OPERATOR literals SEMICOLON;
id_selector: ID_IDENT;
class_selector: CLASS_IDENT;
tag_selector: LOWER_IDENT;
selector: id_selector | class_selector | tag_selector;
variable_reference: CAPITAL_IDENT;

literals: size_literal | bool_literal | color_literal;
size_literal: scalar_literal | pixel_literal | percentage_literal;
scalar_literal: SCALAR;
pixel_literal: PIXELSIZE;
percentage_literal: PERCENTAGE;
bool_literal: TRUE | FALSE;
color_literal: COLOR;

stylerule: selector OPEN_BRACE (declaration | if_statement)* CLOSE_BRACE;

declaration: color_declaration | size_declaration;
size_declaration: size_property (size_literal | variable_reference | expression) SEMICOLON;
color_declaration: color_property (color_literal | variable_reference | expression) SEMICOLON;

color_property: COLOR_PROPERTIES;
size_property: SIZE_PROPERTIES;

// maak een rule om een wiskundige formule uit te kunnen voeren in een declaration check de notepad *wink wink nudge nudge*
expression: expression (PLUS | MIN) expression | (size_literal | variable_reference | mul_expression);
mul_expression: (size_literal | variable_reference) MUL (size_literal | variable_reference);

// if statement
if_statement: if OPEN_BRACE (declaration | if_statement | variable_assignment)* CLOSE_BRACE else?;
if: IF BOX_BRACKET_OPEN (bool_literal | variable_reference) BOX_BRACKET_CLOSE;
else: ELSE OPEN_BRACE (declaration | if_statement | variable_assignment)* CLOSE_BRACE;

//IF: 'if';
//ELSE: 'else';
//BOX_BRACKET_OPEN: '[';
//BOX_BRACKET_CLOSE: ']';