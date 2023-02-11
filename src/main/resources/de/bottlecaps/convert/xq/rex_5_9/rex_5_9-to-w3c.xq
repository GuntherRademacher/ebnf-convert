module namespace x="de/bottlecaps/convert/xq/rex_5_9/rex_5_9-to-w3c.xq";

declare function x:rex_5_9-to-w3c($nodes as node()*) as node()*
{
  for $node in $nodes
  return
    typeswitch ($node)
    case element(Number) return
      text {replace($node, "[^ \t\n\r]", " ")}
    case element(Constraint) return
      text {replace($node, "[^ \t\n\r]", " ")}
    case element() return
      element {node-name($node)} {$node/@*, x:rex_5_9-to-w3c($node/node())}
    default return
      $node
};
