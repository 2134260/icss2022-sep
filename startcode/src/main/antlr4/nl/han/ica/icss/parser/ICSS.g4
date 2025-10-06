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
stylesheet: (variable | property)+;

variable: CAPITAL_IDENT ASSIGNMENT_OPERATOR (size_types | COLOR | boolean) SEMICOLON;
identifier: ID_IDENT | CLASS_IDENT | LOWER_IDENT | CAPITAL_IDENT;

size_types: SCALAR | PIXELSIZE | PERCENTAGE;
boolean: TRUE | FALSE;

property: identifier OPEN_BRACE (color_declaration | size_declaration)* CLOSE_BRACE;
size_declaration: SIZE_PROPERTIES (size_types | variable) SEMICOLON;
color_declaration: COLOR_PROPERTIES (COLOR | variable) SEMICOLON;
