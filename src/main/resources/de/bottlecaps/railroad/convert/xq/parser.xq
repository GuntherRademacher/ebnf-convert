module namespace p="de/bottlecaps/railroad/convert/xq/parser.xq";

declare namespace g="http://www.w3.org/2001/03/XPath/grammar";

declare namespace w3c="de/bottlecaps/railroad/convert/w3c/W3c";
declare namespace abnf="de/bottlecaps/railroad/convert/abnf/Abnf";
declare namespace antlr_3="de/bottlecaps/railroad/convert/antlr_3/Antlr_3";
declare namespace antlr_4="de/bottlecaps/railroad/convert/antlr_4/Antlr_4";
declare namespace bison="de/bottlecaps/railroad/convert/bison/Bison";
declare namespace gold="de/bottlecaps/railroad/convert/gold/Gold";
declare namespace javacc="de/bottlecaps/railroad/convert/javacc/Javacc";
declare namespace instaparse="de/bottlecaps/railroad/convert/instaparse/Instaparse";
declare namespace ixml="de/bottlecaps/railroad/convert/ixml/IXML";
declare namespace jison="de/bottlecaps/railroad/convert/jison/Jison";
declare namespace pegjs="de/bottlecaps/railroad/convert/pegjs/Pegjs";
declare namespace pss="de/bottlecaps/railroad/convert/pss/Pss";
declare namespace phythia="de/bottlecaps/railroad/convert/phythia/Phythia";
declare namespace rex_5_9="de/bottlecaps/railroad/convert/rex_5_9/REx_5_9";
declare namespace xtext="de/bottlecaps/railroad/convert/xtext/Xtext";

import module namespace w3c-to-w3c="de/bottlecaps/railroad/convert/xq/w3c/w3c-to-w3c.xq" at "w3c/w3c-to-w3c.xq";
import module namespace abnf-to-w3c="de/bottlecaps/railroad/convert/xq/abnf/abnf-to-w3c.xq" at "abnf/abnf-to-w3c.xq";
import module namespace antlr_3-to-w3c="de/bottlecaps/railroad/convert/xq/antlr_3/antlr_3-to-w3c.xq" at "antlr_3/antlr_3-to-w3c.xq";
import module namespace antlr_4-to-w3c="de/bottlecaps/railroad/convert/xq/antlr_4/antlr_4-to-w3c.xq" at "antlr_4/antlr_4-to-w3c.xq";
import module namespace bison-to-w3c="de/bottlecaps/railroad/convert/xq/bison/bison-to-w3c.xq" at "bison/bison-to-w3c.xq";
import module namespace gold-to-w3c="de/bottlecaps/railroad/convert/xq/gold/gold-to-w3c.xq" at "gold/gold-to-w3c.xq";
import module namespace instaparse-to-w3c="de/bottlecaps/railroad/convert/xq/instaparse/instaparse-to-w3c.xq" at "instaparse/instaparse-to-w3c.xq";
import module namespace ixml-to-w3c="de/bottlecaps/railroad/convert/xq/ixml/ixml-to-w3c.xq" at "ixml/ixml-to-w3c.xq";
import module namespace javacc-to-w3c="de/bottlecaps/railroad/convert/xq/javacc/javacc-to-w3c.xq" at "javacc/javacc-to-w3c.xq";
import module namespace jison-to-w3c="de/bottlecaps/railroad/convert/xq/jison/jison-to-w3c.xq" at "jison/jison-to-w3c.xq";
import module namespace pegjs-to-w3c="de/bottlecaps/railroad/convert/xq/pegjs/pegjs-to-w3c.xq" at "pegjs/pegjs-to-w3c.xq";
import module namespace pss-to-w3c="de/bottlecaps/railroad/convert/xq/pss/pss-to-w3c.xq" at "pss/pss-to-w3c.xq";
import module namespace phythia-to-w3c="de/bottlecaps/railroad/convert/xq/phythia/phythia-to-w3c.xq" at "phythia/phythia-to-w3c.xq";
import module namespace rex_5_9-to-w3c="de/bottlecaps/railroad/convert/xq/rex_5_9/rex_5_9-to-w3c.xq" at "rex_5_9/rex_5_9-to-w3c.xq";
import module namespace xtext-to-w3c="de/bottlecaps/railroad/convert/xq/xtext/xtext-to-w3c.xq" at "xtext/xtext-to-w3c.xq";

declare variable $p:languages :=
(
  map {"name": "w3c",        "parser": w3c:parse-Grammar#1,         "converter": w3c-to-w3c:w3c-to-w3c#1},
  map {"name": "abnf",       "parser": abnf:parse-rulelist#1,       "converter": abnf-to-w3c:abnf-to-w3c#1},
  map {"name": "antlr_3",    "parser": antlr_3:parse-grammar_#1,    "converter": antlr_3-to-w3c:antlr_3-to-w3c#1},
  map {"name": "antlr_4",    "parser": antlr_4:parse-grammarSpec#1, "converter": antlr_4-to-w3c:antlr_4-to-w3c#1},
  map {"name": "bison",      "parser": bison:parse-input#1,         "converter": bison-to-w3c:bison-to-w3c#1},
  map {"name": "gold",       "parser": gold:parse-Grammar#1,        "converter": gold-to-w3c:gold-to-w3c#1},
  map {"name": "javacc",     "parser": javacc:parse-javacc_input#1, "converter": javacc-to-w3c:javacc-to-w3c#1},
  map {"name": "instaparse", "parser": instaparse:parse-rules#1,    "converter": instaparse-to-w3c:instaparse-to-w3c#1},
  map {"name": "ixml",       "parser": ixml:parse-ixml#1,           "converter": ixml-to-w3c:ixml-to-w3c#1},
  map {"name": "jison",      "parser": jison:parse-jison#1,         "converter": jison-to-w3c:jison-to-w3c#1},
  map {"name": "pegjs",      "parser": pegjs:parse-Grammar#1,       "converter": pegjs-to-w3c:pegjs-to-w3c#1},
  map {"name": "pss",        "parser": pss:parse-PSS-Grammar#1,     "converter": pss-to-w3c:pss-to-w3c#1},
  map {"name": "phythia",    "parser": phythia:parse-Grammar#1,     "converter": phythia-to-w3c:phythia-to-w3c#1},
  map {"name": "rex_5_9",    "parser": rex_5_9:parse-Grammar#1,     "converter": rex_5_9-to-w3c:rex_5_9-to-w3c#1},
  map {"name": "xtext",      "parser": xtext:parse-Grammar#1,       "converter": xtext-to-w3c:xtext-to-w3c#1}
);

declare function p:parse($input)
{
  p:parse($input, $p:languages, ())
};

declare function p:parse($input, $languages, $errors)
{
  if (empty($languages)) then
    <ERRORS>{$errors}</ERRORS>
  else
    let $parse-tree := $languages[1]?parser($input)
    return
      if (empty($parse-tree/self::ERROR)) then
        let $ast :=
          try
          {
            $languages[1]?converter($parse-tree)
          }
          catch *
          {
            let $message :=
              concat
              (
                "grammar was parsed OK, but conversion ", $languages[1]?name, "-to-w3c failed:",
                replace
                (
                  concat
                  (
                    "&#xA;", string-join(($err:code, $err:value, $err:description), " "),
                    "&#xA;at ", $err:module, "(", $err:line-number, ",", $err:column-number, ")",
                    ("&#xA;", $err:additional)[$err:additional]
                  ),
                  "&#xA;",
                  "&#xA;  "
                )
              )
            return <ERRORS><ERROR>{$message}</ERROR></ERRORS>
          }
        return
          element g:grammar
          {
            $ast/@*,
            attribute generator {concat($languages[1]?name, "-to-w3c")}[$languages[1]?name ne "w3c"],
            $ast/node()
          }
      else
        p:parse($input, $languages[position() > 1], ($errors, element {$languages[1]?name} {$parse-tree}))
};
