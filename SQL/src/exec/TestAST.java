package exec;

import static org.junit.Assert.*;

import org.junit.Test;

import exec.AST;

public class TestAST {

	@Test
	public void testEqual() {
		AST asTtree = new AST();
		asTtree.parse("companyName == HTSC");
		assertEquals("Select * from table where companyName = 'HTSC'", asTtree.getSQL());
	}
	
	@Test
	public void testInEqual() {
		AST asTtree = new AST();
		asTtree.parse("companyName <> HTSC");
		assertEquals("Select * from table where companyName <> 'HTSC'", asTtree.getSQL());
	}
	
	@Test
	public void testBigger() {
		AST asTtree = new AST();
		asTtree.parse("companyName > HTSC");
		assertEquals("Select * from table where companyName > 'HTSC'", asTtree.getSQL());
	}
	
	@Test
	public void testSmaller() {
		AST asTtree = new AST();
		asTtree.parse("companyName < HTSC");
		assertEquals("Select * from table where companyName < 'HTSC'", asTtree.getSQL());
	}
	
	@Test
	public void testsinglequote() {
		AST asTtree = new AST();
		asTtree.parse("()");
		assertEquals("", asTtree.getSQL());
	}
	
	@Test
	public void testdoublequote() {
		AST asTtree = new AST();
		asTtree.parse("(())");
		assertEquals("", asTtree.getSQL());
	}
	
	@Test
	public void testquote() {
		AST asTtree = new AST();
		asTtree.parse("(companyName <> HTSC)");
		assertEquals("Select * from table where companyName <> 'HTSC'", asTtree.getSQL());
	}
	
	@Test
	public void testOR() {
		AST asTtree = new AST();
		asTtree.parse("(companyName <> HTSC)OR(age == 30)");
		assertEquals("Select * from table where (companyName <> 'HTSC' OR age = '30')", asTtree.getSQL());
	}
	
	@Test
	public void testAND() {
		AST asTtree = new AST();
		asTtree.parse("(companyName <> HTSC)AND(age == 30)");
		assertEquals("Select * from table where (companyName <> 'HTSC' AND age = '30')", asTtree.getSQL());
	}
	
	@Test
	public void testORAND() {
		AST asTtree = new AST();
		asTtree.parse("( companyName <> HTSC )  OR  ( ( age == 30 ) AND ( sex <> 'Male' ) )");
		assertEquals("Select * from table where (companyName <> 'HTSC' OR (age = '30' AND sex <> 'Male'))", asTtree.getSQL());
	}
	
	@Test
	public void testcomplex() {
		AST asTtree = new AST();
		asTtree.parse("( companyName <> HTSC )  OR  ( ( age > 30 ) AND ( sex <> 'Male' ) )");
		assertEquals("Select * from table where (companyName <> 'HTSC' OR (age > '30' AND sex <> 'Male'))", asTtree.getSQL());
	}

}
