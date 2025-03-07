module netcap {
	requires jpcap;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.media;
	requires javafx.swing;
	requires javafx.swt;
	requires javafx.web;
	exports src to javafx.base,javafx.graphics;
	opens src to javafx.base;
}