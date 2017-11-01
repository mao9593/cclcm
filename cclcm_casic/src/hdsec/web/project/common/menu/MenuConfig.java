package hdsec.web.project.common.menu;

import hdsec.web.project.user.model.SecOperation;
import hdsec.web.project.user.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.casic.panelbar.EffectConstants;
import com.casic.panelbar.Group;
import com.casic.panelbar.IconPositionConstants;
import com.casic.panelbar.Item;
import com.casic.panelbar.PanelBar;

/**
 * 说明: 配置菜单
 *
 * @author renmingfei
 */

public class MenuConfig {
	//private Logger logger = Logger.getLogger(this.getClass());
	private List<MenuButton> menus = null;
	
	public List<MenuButton> getMenus() {
		return menus;
	}
	
	/**
	 * Description :读取菜单各级项目并配置
	 *
	 */
	private MenuConfig(HttpServletRequest request, Collection<String> operCodes, UserService userService) {
		List<MenuButton> menuList = null;
		//读取大菜单列表
		List<SecOperation> menuOperList = userService.getAllMenuOper();
		menuList = new ArrayList<MenuButton>(menuOperList.size());
		int menuLen = 0;
		for (SecOperation menuOper : menuOperList) {
			MenuButton menu = new MenuButton();
			menu.setCaption(menuOper.getOper_name());
			menu.setUrl(menuOper.getWeb_url());
			menu.setIcon(menuOper.getIcon_path());
			if (operCodes.contains(menuOper.getOper_code())) {
				menuLen++;
				menu.setOnclick(menuOper.getOper_code());
			} else {
				continue;
			}
			//读取大菜单下面板的列表
			List<SecOperation> panelOperList = userService.getSubOperByCode(menuOper.getOper_code());
			List<PanelGroup> panelList = new ArrayList<PanelGroup>(panelOperList.size());
			for (SecOperation panelOper : panelOperList) {
				if (!operCodes.contains(panelOper.getOper_code())) {
					continue;
				}
				PanelGroup panel = new PanelGroup();
				panel.setCaption(panelOper.getOper_name());
				//读取面板下的具体菜单项
				List<SecOperation> itemOperList = userService.getSubOperByCode(panelOper.getOper_code());
				List<PanelItem> itemList = new ArrayList<PanelItem>(itemOperList.size());
				for (SecOperation itemOper : itemOperList) {
					if (!operCodes.contains(itemOper.getOper_code())) {
						continue;
					}
					PanelItem item = new PanelItem();
					item.setCaption(itemOper.getOper_name());
					item.setUrl(itemOper.getWeb_url());
					item.setIcon(itemOper.getIcon_path());
					itemList.add(item);
				}
				panel.setItems(itemList);
				panelList.add(panel);
			}
			menu.setPanels(panelList);
			menuList.add(menu);
		}
		request.setAttribute("menuLen", String.valueOf(menuLen));
		menus = menuList;
	}
	
	public static MenuConfig getInstance(HttpServletRequest request, Collection<String> operCodes,
			UserService userService) {
		return new MenuConfig(request, operCodes, userService);
	}
	
	private MenuButton getMenuButton(int menuId) {
		if (menus != null && menus.size() > 0) {
			return menus.get(menuId);
		} else {
			return null;
		}
	}
	
	/**
	 * Description :对菜单进行设置
	 */
	public PanelBar getPanelBar(int menuId, String defTargetName, String panelIdPreffix, HttpServletRequest request)
			throws Exception {
		PanelBar panelBar = new PanelBar();
		MenuButton menuButton = getMenuButton(menuId);
		if (menuButton == null) {
			return null;
		}
		List<PanelGroup> panels = menuButton.getPanels();
		
		//用来判断浏览器类型
		panelBar.setUserAgent(request.getHeader("User-Agent"));
		//注册序列号
		panelBar.setUserData("WarBaby:MetarNet:2152752877");
		//菜单大小自动适应页面
		panelBar.setAutoSizeToFrame(true);
		//菜单是否跟随网页滚动
		panelBar.setFollowYScroll(false);
		//展开时是否有动画效果
		panelBar.setEffect(EffectConstants.Slide);
		
		//菜单全局设置: 边框, 背景, 图片, 文字等
		panelBar.setBorderSize(0);
		panelBar.setBackgroundColor("#E7EEF7");
		panelBar.setClearPixelImage("images/_component/panelbar/clearpixel.gif");
		panelBar.setLoadingText("页面加载中...");
		
		//按钮设置: 边框, 高度, 图标大小, 图表位置, 按钮背景, 选中的按钮背景
		panelBar.setButtonBorderSize(0);
		panelBar.setButtonHeight(31);
		panelBar.setIconHeight(32);
		panelBar.setIconWidth(32);
		panelBar.setIconPosition(IconPositionConstants.Left);
		panelBar.setButtonBackgroundImage("images/_component/panelbar/HeaderExpandedBg.gif");
		panelBar.setSelectedButtonBackgroundImage("images/_component/panelbar/HeaderExpandedBg.gif");
		
		//字体样式设置
		panelBar.getButtonFont().setColor("#FFFFFF");
		panelBar.getButtonFont().setWeight("bold");
		panelBar.getButtonHoverFont().setColor("#715c1f");
		panelBar.getButtonHoverFont().setWeight("bold");
		panelBar.getItemFont().setAlignment("left");
		panelBar.getItemFont().setColor("black");
		panelBar.getItemFont().setPaddingLeft(8);
		panelBar.getItemHoverFont().setColor("#715c1f");
		panelBar.getItemHoverFont().setTextDecoration("none");
		
		//滚动箭头图标设置
		panelBar.setScrollDownActiveImage("images/_component/panelbar/scroll_down_enable.gif");
		panelBar.setScrollDownDisabledImage("images/_component/panelbar/scroll_disable.gif");
		panelBar.setScrollDownEnabledImage("images/_component/panelbar/scroll_down_active.gif");
		panelBar.setScrollUpActiveImage("images/_component/panelbar/scroll_up_enable.gif");
		panelBar.setScrollUpDisabledImage("images/_component/panelbar/scroll_disable.gif");
		panelBar.setScrollUpEnabledImage("images/_component/panelbar/scroll_up_active.gif");
		
		for (int i = 0; i < panels.size(); i++) {
			Group panelGroup = panelBar.getGroups().Add();
			PanelGroup eomsPanelGroup = panels.get(i);
			panelGroup.setCaption(eomsPanelGroup.getCaption());
			panelGroup.setID(panelIdPreffix + i);
			panelGroup.setOnClick("panelGroupClicked(" + i + ")");
			List<PanelItem> items = eomsPanelGroup.getItems();
			
			for (int j = 0; j < items.size(); j++) {
				PanelItem eomsPanelItem = items.get(j);
				Item item = panelGroup.getItems().Add();
				item.setCaption(eomsPanelItem.getCaption());
				item.setURL(eomsPanelItem.getUrl());
				if (item.getURL().startsWith("/") && !item.getURL().toLowerCase().startsWith("http")) {
					item.setURL(request.getContextPath() + item.getURL());
				}
				item.setIconImage(eomsPanelItem.getIcon());
				item.setTarget(defTargetName);
				if (eomsPanelItem.getTarget() != null && eomsPanelItem.getTarget().length() > 0)
					item.setTarget(eomsPanelItem.getTarget());
				item.setOnClick("panelItemClicked('" + j + "')");
			}
		}
		return panelBar;
	}
	
	public static void reload() {
		//TODO:instance = null;
	}
}
