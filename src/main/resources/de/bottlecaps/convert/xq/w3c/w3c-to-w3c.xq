module namespace x="de/bottlecaps/convert/xq/w3c/w3c-to-w3c.xq";

import module namespace a="de/bottlecaps/railroad/xq/cst-to-ast.xq" at "../../../railroad/xq/cst-to-ast.xq";
import module namespace n="de/bottlecaps/railroad/xq/normalize-ast.xq" at "../../../railroad/xq/normalize-ast.xq";

declare namespace g="http://www.w3.org/2001/03/XPath/grammar";

declare function x:w3c-to-w3c($node as element(Grammar)) as element(g:grammar)
{
  a:ast($node)
};
