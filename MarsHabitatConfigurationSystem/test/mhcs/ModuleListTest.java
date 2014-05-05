package mhcs;
//
//import com.google.gwt.junit.client.GWTTestCase;
//
//import mhcs.client.module.Module;
//import mhcs.client.module.ModuleList;
//
//public class ModuleListTest {
//
//	
//	public static final int FIVE = 5;
//	public static final int TEN = 10;
//	public static final int BOUNDS = 200;
//	
//	ModuleList list;
//	
//	public ModuleListTest() {}
//	
//	@BeforeClass
//	public void setupTest() {
//		this.list = new ModuleList();
//	}
//	
//	@Before
//	public void clearList() {
//		this.list = new ModuleList();
//	}
//	
//	@Test(expected = IllegalArgumentException.class)
//	public void testAddSameModuleCode() {
//		this.list.addModule(new Module(1, FIVE, FIVE, 0, Module.DAMAGED));
//		this.list.addModule(new Module(1, TEN, TEN, 0, Module.UNDAMAGED));
//	}
//	
//	@Test(expected = IllegalArgumentException.class)
//	public void testAddSameModuleCoord() {
//		this.list.addModule(new Module(1, FIVE, FIVE, 0, Module.DAMAGED));
//		this.list.addModule(new Module(2, FIVE, FIVE, 0, Module.UNDAMAGED));
//	}
//	
//	@Test(expected = IndexOutOfBoundsException.class)
//	public void testGetWrongModule() {
//		this.list.addModule(new Module(1, FIVE, FIVE, 0, Module.DAMAGED));
//		this.list.getModule(BOUNDS);
//	}
//	
//	@Test
//	public void testGetModule() {
//		this.list.addModule(new Module(1, FIVE, FIVE, 0, Module.DAMAGED));
//		Module module = this.list.getModule(1);
//		assertEquals(module.getCode(), 1);
//		assertEquals(module.getXCoord(), FIVE);
//		assertEquals(module.getYCoord(), FIVE);
//		assertEquals(module.getTurns(), 0);
//		assertEquals(module.getStatus(), Module.DAMAGED);
//	}
//	
//	@Test
//	public void testDeleteModule() {
//		this.list.addModule(new Module(1, FIVE, FIVE, 0, Module.DAMAGED));
//		this.list.deleteModule(1);
//		assertEquals(this.list.getModule(1), null);
//	}
//
//	@Override
//	public String getModuleName() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}

