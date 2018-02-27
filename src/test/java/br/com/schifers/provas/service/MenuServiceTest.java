package br.com.schifers.provas.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.schifers.provas.builder.MenuBuilder;
import br.com.schifers.provas.builder.MenuItemBuilder;
import br.com.schifers.provas.builder.MenuMenuItemBuilder;
import br.com.schifers.provas.builder.MenuTypeBuilder;
import br.com.schifers.provas.dao.MenuDAO;
import br.com.schifers.provas.dao.MenuItemDAO;
import br.com.schifers.provas.dao.MenuMenuItemDAO;
import br.com.schifers.provas.dto.MenuDTO;
import br.com.schifers.provas.dto.MenuItemDTO;
import br.com.schifers.provas.entity.Menu;
import br.com.schifers.provas.entity.MenuItem;
import br.com.schifers.provas.entity.MenuMenuItem;
import br.com.schifers.provas.entity.MenuType;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {

	@Mock
	private MenuDAO menuDao;

	@Mock
	private MenuItemDAO menuItemDao;

	@Mock
	private MenuMenuItemDAO menuMenuItemDao;

	@InjectMocks
	private MenuService menuService;

	@Test
	public void testBuildMenu() {
		Menu menu = new MenuBuilder().id(1L).name("principal").build();

		MenuType itemType = new MenuTypeBuilder().id(1L).name("ITEM").build();

		MenuType listType = new MenuTypeBuilder().id(2L).name("LIST").build();

		MenuItem root1 = new MenuItemBuilder().id(1L).name("root1").menuType(itemType).build();
		MenuItem root2 = new MenuItemBuilder().id(2L).name("root2").menuType(listType).build();
		MenuItem root3 = new MenuItemBuilder().id(3L).name("root3").menuType(listType).build();

		MenuItem root2child1 = new MenuItemBuilder().id(4L).name("root2child1").menuType(itemType).parent(root2)
				.build();
		MenuItem root2child2 = new MenuItemBuilder().id(5L).name("root2child2").menuType(listType).parent(root2)
				.build();
		MenuItem root2child3 = new MenuItemBuilder().id(6L).name("root2child3").menuType(listType).parent(root2)
				.build();

		MenuItem root2child2child1 = new MenuItemBuilder().id(7L).name("root2child2child1").menuType(itemType)
				.parent(root2child2).build();
		MenuItem root2child3child1 = new MenuItemBuilder().id(8L).name("root2child3child1").menuType(itemType)
				.parent(root2child3).build();

		MenuItem root3child1 = new MenuItemBuilder().id(9L).name("root3child1").menuType(listType).parent(root3)
				.build();
		MenuItem root3child2 = new MenuItemBuilder().id(10L).name("root3child2").menuType(itemType).parent(root3)
				.build();

		MenuItem root3child1child1 = new MenuItemBuilder().id(11L).name("root3child1child1").menuType(itemType)
				.parent(root3child1).build();

		List<MenuItem> menuItems = new ArrayList<MenuItem>();

		menuItems.add(root1);
		menuItems.add(root2);
		menuItems.add(root3);
		menuItems.add(root2child1);
		menuItems.add(root2child2);
		menuItems.add(root2child3);
		menuItems.add(root2child2child1);
		menuItems.add(root2child3child1);
		menuItems.add(root3child1);
		menuItems.add(root3child2);
		menuItems.add(root3child1child1);

		MenuMenuItem link01 = new MenuMenuItemBuilder().id(1L).menu(menu).menuItem(root1).ordering(0).build();
		MenuMenuItem link02 = new MenuMenuItemBuilder().id(2L).menu(menu).menuItem(root2).ordering(1).build();
		MenuMenuItem link03 = new MenuMenuItemBuilder().id(3L).menu(menu).menuItem(root3).ordering(2).build();
		MenuMenuItem link04 = new MenuMenuItemBuilder().id(4L).menu(menu).menuItem(root2child1).ordering(2).build();
		MenuMenuItem link05 = new MenuMenuItemBuilder().id(5L).menu(menu).menuItem(root2child2).ordering(1).build();
		MenuMenuItem link06 = new MenuMenuItemBuilder().id(6L).menu(menu).menuItem(root2child3).ordering(0).build();
		MenuMenuItem link07 = new MenuMenuItemBuilder().id(7L).menu(menu).menuItem(root2child2child1).ordering(0)
				.build();
		MenuMenuItem link08 = new MenuMenuItemBuilder().id(8L).menu(menu).menuItem(root2child3child1).ordering(0)
				.build();
		MenuMenuItem link09 = new MenuMenuItemBuilder().id(9L).menu(menu).menuItem(root3child1).ordering(1).build();
		MenuMenuItem link10 = new MenuMenuItemBuilder().id(10L).menu(menu).menuItem(root3child2).ordering(0).build();
		MenuMenuItem link11 = new MenuMenuItemBuilder().id(11L).menu(menu).menuItem(root3child1child1).ordering(0)
				.build();

		Mockito.when(menuDao.findByName(Mockito.anyString())).thenReturn(menu);

		Mockito.when(menuItemDao.findByMenu(menu.getId())).thenReturn(menuItems);

		Mockito.when(menuMenuItemDao.findByMenuByMenuItem(menu.getId(), root1.getId())).thenReturn(link01);
		Mockito.when(menuMenuItemDao.findByMenuByMenuItem(menu.getId(), root2.getId())).thenReturn(link02);
		Mockito.when(menuMenuItemDao.findByMenuByMenuItem(menu.getId(), root3.getId())).thenReturn(link03);
		Mockito.when(menuMenuItemDao.findByMenuByMenuItem(menu.getId(), root2child1.getId())).thenReturn(link04);
		Mockito.when(menuMenuItemDao.findByMenuByMenuItem(menu.getId(), root2child2.getId())).thenReturn(link05);
		Mockito.when(menuMenuItemDao.findByMenuByMenuItem(menu.getId(), root2child3.getId())).thenReturn(link06);
		Mockito.when(menuMenuItemDao.findByMenuByMenuItem(menu.getId(), root2child2child1.getId())).thenReturn(link07);
		Mockito.when(menuMenuItemDao.findByMenuByMenuItem(menu.getId(), root2child3child1.getId())).thenReturn(link08);
		Mockito.when(menuMenuItemDao.findByMenuByMenuItem(menu.getId(), root3child1.getId())).thenReturn(link09);
		Mockito.when(menuMenuItemDao.findByMenuByMenuItem(menu.getId(), root3child2.getId())).thenReturn(link10);
		Mockito.when(menuMenuItemDao.findByMenuByMenuItem(menu.getId(), root3child1child1.getId())).thenReturn(link11);

		MenuDTO dto = menuService.buildMenu("principal");

		List<MenuItemDTO> menusWithLevel = dto.buildMenusWithLevel();

		for (MenuItemDTO menuItemDTO : menusWithLevel) {
			for (int i = 0; i < (menuItemDTO.getLevel() * 3); i++) {
				System.out.print("-");
			}
			System.out.println((menuItemDTO.getLevel() > 0 ? " " : "") + menuItemDTO.getName());
		}

		Assert.assertNotNull(dto);
		Assert.assertTrue(dto.getRoots().size() == 3);
		Assert.assertTrue(dto.getRoots().get(0).getName().equals("root1"));
		Assert.assertTrue(dto.getRoots().get(1).getName().equals("root2"));
		Assert.assertTrue(dto.getRoots().get(2).getName().equals("root3"));
		Assert.assertTrue(dto.getMenuItemsMap().size() == 11);
		Assert.assertTrue(dto.getMenuItemsMap().get(root1.getId()).size() == 0);
		Assert.assertTrue(dto.getMenuItemsMap().get(root2.getId()).size() == 3);
		Assert.assertTrue(dto.getMenuItemsMap().get(root3.getId()).size() == 2);
		Assert.assertTrue(dto.getMenuItemsMap().get(root2child1.getId()).size() == 0);
		Assert.assertTrue(dto.getMenuItemsMap().get(root2child2.getId()).size() == 1);
		Assert.assertTrue(dto.getMenuItemsMap().get(root2child3.getId()).size() == 1);
		Assert.assertTrue(dto.getMenuItemsMap().get(root2child2child1.getId()).size() == 0);
		Assert.assertTrue(dto.getMenuItemsMap().get(root2child3child1.getId()).size() == 0);
		Assert.assertTrue(dto.getMenuItemsMap().get(root3child1.getId()).size() == 1);
		Assert.assertTrue(dto.getMenuItemsMap().get(root3child2.getId()).size() == 0);
		Assert.assertTrue(dto.getMenuItemsMap().get(root3child1child1.getId()).size() == 0);

	}

}
