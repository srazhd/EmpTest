<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet 
	version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo">

	<xsl:template match="empAttendanceReport">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			 
			<!-- PAGE SETUP -->
			<fo:layout-master-set>
				<!-- PAGE MASTER -->
				<fo:simple-page-master master-name="A4" page-height="29.02cm" page-width="29.02cm" margin-top="6mm" margin-bottom="6mm" margin-left="6mm" margin-right="6mm">
					<fo:region-body margin-top="18mm" margin-left="0mm" margin-right="0mm" margin-bottom="5mm" />
					<fo:region-before region-name="header-first" extent="18mm"/>
					<fo:region-after region-name="footer-pagenumber" extent="4.5mm"/>
				</fo:simple-page-master>
				<fo:simple-page-master master-name="A4-rest" page-height="21cm" page-width="29.02cm" margin-top="6mm" margin-bottom="6mm" margin-left="6mm" margin-right="6mm">
					<fo:region-body margin-top="0mm" margin-left="0mm" margin-right="0mm" margin-bottom="5mm" />
					<fo:region-after region-name="footer-pagenumber" extent="4.5mm"/>
				</fo:simple-page-master>
				<fo:simple-page-master master-name="A4-last" page-height="21cm" page-width="29.02cm" margin-top="6mm" margin-bottom="6mm" margin-left="6mm" margin-right="6mm">
					<fo:region-body margin-top="0mm" margin-left="0mm" margin-right="0mm" margin-bottom="5mm" />
					<fo:region-after region-name="footer-pagenumber" extent="4.5mm"/>
				</fo:simple-page-master>

				<!-- PAGE SEQUENCE DECLARATION -->
				<fo:page-sequence-master master-name="document">
					<fo:repeatable-page-master-alternatives>
						<fo:conditional-page-master-reference page-position="first" master-reference="A4" />
						<fo:conditional-page-master-reference page-position="rest" master-reference="A4-rest" />
						<fo:conditional-page-master-reference page-position="last" master-reference="A4-last" />
					</fo:repeatable-page-master-alternatives>
				</fo:page-sequence-master>
			</fo:layout-master-set>

			<!-- PAGE SEQUENCE -->
			<fo:page-sequence master-reference="document">

				

				<!-- BODY CONTENT -->
 				<fo:flow flow-name="xsl-region-body">
					<fo:block-container width="100%" margin-top="0px" right="0mm">
						<fo:block text-align="center">
							Employee Attendance List
						</fo:block>
						
						<fo:block>
							 
									<xsl:apply-templates select="empAttendances"/>
								 
						</fo:block>
					</fo:block-container>
					 
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

	<!-- Category table template -->
	<xsl:template match="empAttendances">
		 	
			
				 
					<fo:table table-layout="fixed" width="100%" border-collapse="collapse" >
						<fo:table-column column-width="8%"/>
						<fo:table-column column-width="15%" />
						<fo:table-column column-width="8%" />
						<fo:table-column column-width="5%"/>
						<fo:table-column column-width="14%"/>
						<fo:table-column column-width="9%"/>
						<fo:table-column column-width="15%"/>
						<fo:table-column column-width="15%"/>
						<fo:table-column column-width="10%"/>
						<!-- Table header -->
						<fo:table-header xsl:use-attribute-sets="table.font.size">
							<fo:table-row>
								<fo:table-cell xsl:use-attribute-sets="client.table.th">
									<fo:block>month</fo:block>
								</fo:table-cell>
								<fo:table-cell xsl:use-attribute-sets="client.table.th" text-align="center">
									<fo:block>date</fo:block>
								</fo:table-cell>
								<fo:table-cell xsl:use-attribute-sets="client.table.th" text-align="right">
									<fo:block>day</fo:block>
								</fo:table-cell>
								<fo:table-cell xsl:use-attribute-sets="client.table.th" text-align="right">
									<fo:block>id</fo:block>
								</fo:table-cell>
								<fo:table-cell xsl:use-attribute-sets="client.table.th" text-align="right">
									<fo:block>employeeName</fo:block>
								</fo:table-cell>
								<fo:table-cell xsl:use-attribute-sets="client.table.th" text-align="right">
									<fo:block>department</fo:block>
								</fo:table-cell>
								<fo:table-cell xsl:use-attribute-sets="client.table.th" text-align="right">
									<fo:block>firstInTime</fo:block>
								</fo:table-cell>
								<fo:table-cell xsl:use-attribute-sets="client.table.th" text-align="right">
									<fo:block>lastOutTime</fo:block>
								</fo:table-cell>
								<fo:table-cell xsl:use-attribute-sets="client.table.th" text-align="right">
									<fo:block>hoursOfWork</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-header>

						<!-- table body -->
						<fo:table-body>
							<xsl:if test="empAttendance">
								<xsl:apply-templates select="empAttendance"/>
							</xsl:if>
							
						</fo:table-body>
					</fo:table>
				 
				
				 
				
			 
	</xsl:template>

	<!-- Item table template -->
	<xsl:template match="empAttendance"> 
		<fo:table-row>
			<fo:table-cell xsl:use-attribute-sets="client.table.td">
				<fo:block>
					<xsl:value-of select="month"/>  
				</fo:block>
			</fo:table-cell>
			<fo:table-cell xsl:use-attribute-sets="client.table.td" text-align="center">
				<fo:block>
					<xsl:value-of select="date"/>
				</fo:block>
			</fo:table-cell>
			<fo:table-cell xsl:use-attribute-sets="client.table.td" text-align="right">
				<fo:block>
					<xsl:value-of select="day"/>
				</fo:block>
			</fo:table-cell>
			<fo:table-cell xsl:use-attribute-sets="client.table.td" text-align="right">
				<fo:block>
					<xsl:value-of select="id"/>
				</fo:block>
			</fo:table-cell>
			<fo:table-cell xsl:use-attribute-sets="client.table.td" text-align="right">
				<fo:block>
					<xsl:value-of select="employeeName"/>
				</fo:block>
			</fo:table-cell>
			
			<fo:table-cell xsl:use-attribute-sets="client.table.td" text-align="right">
				<fo:block>
					<xsl:value-of select="department"/>
				</fo:block>
			</fo:table-cell>
			<fo:table-cell xsl:use-attribute-sets="client.table.td" text-align="right">
				<fo:block>
					<xsl:value-of select="firstInTime"/>
				</fo:block>
			</fo:table-cell>
			<fo:table-cell xsl:use-attribute-sets="client.table.td" text-align="right">
				<fo:block>
					<xsl:value-of select="lastOutTime"/>
				</fo:block>
			</fo:table-cell>
			<fo:table-cell xsl:use-attribute-sets="client.table.td" text-align="right">
				<fo:block>
					<xsl:value-of select="hoursOfWork"/>
				</fo:block>
			</fo:table-cell>
		</fo:table-row> 
	</xsl:template>


	<!-- stylesheets -->
	<xsl:attribute-set name="border.full">
		<xsl:attribute name="border">1px solid #000000</xsl:attribute>
		<xsl:attribute name="float">left</xsl:attribute>
	</xsl:attribute-set>
	<xsl:attribute-set name="table.font.size">
		<xsl:attribute name="font-size">8pt</xsl:attribute>
	</xsl:attribute-set>
	<xsl:attribute-set name="dealer.table.td">
		<xsl:attribute name="padding-top">2px</xsl:attribute>
		<xsl:attribute name="padding-bottom">2px</xsl:attribute>
	</xsl:attribute-set>
	<xsl:attribute-set name="client.table.th">
		<xsl:attribute name="font-weight">bold</xsl:attribute>
		<xsl:attribute name="font-size">8pt</xsl:attribute>
		<xsl:attribute name="padding">2px</xsl:attribute>
		<xsl:attribute name="background-color">#DDDDDD</xsl:attribute>
		<xsl:attribute name="border">1pt solid #000000</xsl:attribute>
		<xsl:attribute name="padding-left">5px</xsl:attribute>
		<xsl:attribute name="padding-right">5px</xsl:attribute>
	</xsl:attribute-set>
	<xsl:attribute-set name="client.table.tf">
		<xsl:attribute name="font-weight">bold</xsl:attribute>
		<xsl:attribute name="font-size">8pt</xsl:attribute>
		<xsl:attribute name="padding">2px</xsl:attribute>
		<xsl:attribute name="border">1pt solid #000000</xsl:attribute>
	</xsl:attribute-set>
	<xsl:attribute-set name="client.table.td">
		<xsl:attribute name="font-size">7pt</xsl:attribute>
		<xsl:attribute name="padding">2px</xsl:attribute>
		<xsl:attribute name="border">1pt solid #000000</xsl:attribute>
		<xsl:attribute name="padding-left">5px</xsl:attribute>
		<xsl:attribute name="padding-right">5px</xsl:attribute>
	</xsl:attribute-set>
	<xsl:attribute-set name="category.table.td">
		<xsl:attribute name="font-size">7pt</xsl:attribute>
		<xsl:attribute name="padding">2px</xsl:attribute>
	</xsl:attribute-set>
</xsl:stylesheet>