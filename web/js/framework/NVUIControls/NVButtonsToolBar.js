function buttonsContainer(jsondata){this.result=function(){var buttonsDiv=$j('<div/>');if(jsondata!=null){$j(jsondata.buttons).each(function(index,buttonData){var buttonGenerated=new button(buttonData);buttonsDiv.append(buttonGenerated.result)})}return buttonsDiv;}}