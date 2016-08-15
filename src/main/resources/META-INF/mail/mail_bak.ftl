<html>  
  <body>  
   <table>  
        <tr><td style="font-size: 15px;">异常类型：<font style="font-size: 13px;" color="red">${unusualType}</font></td></tr>  
        <tr><td style="font-size: 15px;">数据异常时间：<font style="font-size: 13px;" color="red">${errorDate}</font></td></tr>  
        <tr>
        	<td style="font-size: 15px;">  
	        	<div style="float: left;">情况说明：</div><br />
	        	<div style="padding-left:20px;width: 500px;color: #0055dd;font-size: 14px;">${reportExplain}</div>  
	        </td>
        </tr> 
        <tr>
        	<td>
        		<div style="float: left;">收录数基准：</div><br />
	        	<div style="padding-left:20px;width: 500px;font-size: 13px;">
	        		[baidu]:${baiduCount}<br />
	        		[sogou]:${sogouCount}<br />
	        		[so]:${soCount}
	        	</div>
	        </td>
        </tr> 
   </table>  
  </body>  
</html>  