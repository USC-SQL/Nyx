#! /usr/bin/perl
open FILE, "< JTrac_files/jtrac.css";
while(<FILE>)
{
	$str =~ s/$_/background: #ffffff/g;

	print $str;
}
