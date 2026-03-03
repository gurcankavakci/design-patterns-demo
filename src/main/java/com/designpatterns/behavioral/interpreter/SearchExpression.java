package com.designpatterns.behavioral.interpreter;

import com.designpatterns.model.Product;

/**
 * INTERPRETER PATTERN - Yorumlayici Deseni
 *
 * TANIM: Bir dil icin gramer tanimlar ve bu grameri kullanan yorumlayici saglar.
 *
 * PROBLEM: Urun aramalari icin esnek sorgular:
 * "category:Elektronik AND price:0-5000 AND brand:Apple"
 * "category:Giyim OR category:Spor"
 * Elle parse etmek karmasik ve bakimi zor.
 *
 * GRAMMAR:
 * Expression ::= Terminal | AndExpr | OrExpr | NotExpr
 * Terminal    ::= CategoryExpr | PriceRangeExpr | BrandExpr | InStockExpr
 *
 * COMPOSITE ILE ILISKISI:
 * AST (Abstract Syntax Tree) = Composite + Interpreter
 * Terminal = Leaf, NonTerminal (And/Or) = Composite
 *
 * UML DIAGRAM:
 * +-----------------------------+
 * | <<interface>> SearchExpr   |
 * +-----------------------------+
 * |+interpret(product): boolean |
 * |+getDescription(): String    |
 * +-------------+---------------+
 *               |
 *   Terminal:           NonTerminal:
 *   CategoryExpr        AndExpression
 *   PriceRangeExpr      OrExpression
 *   BrandExpr           NotExpression
 *   InStockExpr
 *
 * GERCEK HAYAT KULLANIMI:
 * SQL parser, Regular expressions (java.util.regex), XPath, Template engines
 *
 * AVANTAJLAR:
 * + Gramer genisletmek kolay (yeni Expression sinifi)
 * + Her kural bagimsiz test edilebilir
 *
 * DEZAVANTAJLAR:
 * - Karmasik gramer icin cok sinif
 * - Buyuk gramerler icin verimli degil
 */
public interface SearchExpression {

    /**
     * Verilen urune bu ifadeyi uygular.
     *
     * @param product Filtrelenecek urun
     * @return true: urun eslesirse, false: eslesmezse
     */
    boolean interpret(Product product);

    /**
     * Sorgunun insan tarafindan okunabilir aciklamasini dondurur.
     * Debugging ve loglama icin kullanilir.
     *
     * @return Sorgu aciklamasi (ornek: "category:Elektronik")
     */
    String getDescription();
}
