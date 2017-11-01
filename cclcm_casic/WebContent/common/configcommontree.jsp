<%@ page language="java" pageEncoding="UTF-8" contentType="text/xml;charset=UTF-8" %>
<%@ page import="hdsec.web.project.user.model.SecCommonContact,hdsec.web.project.user.model.SecCommonGroup" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<tree>
    <%
//        System.out.println("-------------------------------常用联系人---start-----------------------------------");
        List groupList = (List) request.getAttribute("groupList");
        //System.out.println("(List) request.getAttribute(\"groupList\") is" + (List) request.getAttribute("groupList"));
        Map groupMap = (Map) request.getAttribute("groupMap");
        //System.out.println("(Map) request.getAttribute(\"groupMap\") is:"+(Map) request.getAttribute("groupMap"));
        // List userContactList = (List) request.getAttribute("userContactList");
        // List deptContactList = (List) request.getAttribute("deptContactList");
//        String selectType = (String) request.getAttribute("selectType");
        //System.out.println("(String) request.getAttribute(\"selectType\") is"+ (String) request.getAttribute("selectType"));


        for (int i = 0; i < groupList.size(); i++) {
            //System.out.println("i is: "+i);
            SecCommonGroup commonGroup = (SecCommonGroup) groupList.get(i);
           //System.out.println("(SecCommonGroup) groupList.get(i) is "+(SecCommonGroup) groupList.get(i));
            List oneGroupContact = (List) groupMap.get(new Integer(commonGroup.getGroup_id()));
          //  System.out.println("(List) groupMap.get(new Integer(commonGroup.getGroup_id())) is "+(List) groupMap.get(new Integer(commonGroup.getGroup_id())));

            if (oneGroupContact!=null&&oneGroupContact.size() > 0) {
          //  System.out.println("进入if语句");
    %>
    <tree nodeType="common_group" selected="false" sid="common_<%=commonGroup.getGroup_id()%>"
          text="<%=commonGroup.getGroup_name()%>">
        <%

            for (int j = 0; j < oneGroupContact.size(); j++) {
                SecCommonContact commonContact = (SecCommonContact) oneGroupContact.get(j);
            //    System.out.println("j is:"+j);
            //    System.out.println("(SecCommonContact) oneGroupContact.get(j) is:"+(SecCommonContact) oneGroupContact.get(j));
                if (commonContact.getContact_type() == 1) {%>
        <tree nodeType="common_contact" selected="false" sid="common_<%=commonContact.getContact_code()%>"
              text="<%=commonContact.getUser_name()%>"/>
        <%
                } else {
              //  System.out.println("进入else语句");
        %>
        <tree nodeType="common_contact" selected="false" sid="common_<%=commonContact.getContact_code()%>"
              text="<%=commonContact.getDept_name()%>"/>
        <%
                    }
                }
            
        %>

    </tree>
    <%}
    }
//    System.out.println("-------------------------------常用联系人---ended-----------------------------------");
    %>
</tree>
	 
