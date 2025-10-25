(:~
 : Generic XML to JSON converter.
 :)
module namespace j="de/bottlecaps/convert/xq/xml-to-json.xq";

declare function j:xml-to-json($node as node()) as item()? {
  typeswitch($node)
    case document-node() return
      map {
        "document": map {
          "*": array { 
            for $n in $node/node()
            let $c := j:xml-to-json($n)
            where exists($c)
            return $c
          }
        }
      }

    case element() return
      let $attributes := map:merge(
        for $a in $node/@*
        return map:entry(name($a), string($a))
      )
      let $children :=
        for $child in $node/node()
        return j:xml-to-json($child)
      return map:entry(
        local-name($node),
        if (exists($children)) then
          map:merge((
            $attributes,
            map:entry("*", array { $children })
          ))
        else
          $attributes
      )

    case text() return
      $node[empty(preceding-sibling::*) and empty(following-sibling::*)]!string()

    case comment() return
      map { "comment": string($node) }

    case processing-instruction() return
      map {
        "processing-instruction": map {
          "target": name($node),
          "data": string($node)
        }
      }

    default return ()
};
