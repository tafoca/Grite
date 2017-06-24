package uds.Grite;

import java.util.ArrayList;
import java.util.Iterator;

public class main {

	public ArrayList<String[]> genIndexUsed(ArrayList<String[]> unitem, ArrayList<String[]> unitemFreq) {
		int n1 = unitem.size(), n2 = unitemFreq.size();
		ArrayList<String[]> valrtn = new ArrayList<>();
		System.out.println(n1 + "  --  " + n2);
		switch (n1) {
		case 4:
			for (int i = 0; i <= 1; i++) {
				for (int j = 2; j < 4; j++) {
					// condition unitem[i] et unitem[j] arptienne a unitemFreq
					// ==>true sinon false
					String[] tm = unitem.get(i);
					String tmst1 = tm[0];
					String tmst2 = unitem.get(j)[0];
					if (!(tmst1 == tmst2)) {
						valrtn.add(lexicalFusion(unitem.get(i), unitem.get(j)));
					}
				}
			}
			break;

		case 6:
			for (int i = 0; i < 2; i++) {
				for (int j = 2; j < 4; j++) {
					for (int k = 4; k < 6; k++) {
						// condition unitem[i] et unitem[j] arptienne a
						// unitemFreq
						// ==>true sinon false
						String[] tm = unitem.get(i);
						String tmst1 = tm[0];
						String tmst2 = unitem.get(j)[0];
						String tmst3 = unitem.get(k)[0];

						if (testitem(tmst1, tmst2, tmst3)) {
							String[] val = lexicalFusion(unitem.get(i), unitem.get(j));
							String[] val3 = unitem.get(k);
							String[] add = Fusion(val, val3);
							valrtn.add(add);
						}
					}

				}
			}
			break;

		case 8:
			for (int i = 0; i < 2; i++) {
				for (int j = 2; j < 4; j++) {
					for (int k = 4; k < 6; k++) {
						for (int l = 6; l < 8; l++) {
							String tmst1 = unitem.get(i)[0];
							String tmst2 = unitem.get(j)[0];
							String tmst3 = unitem.get(k)[0];
							String tmst4 = unitem.get(l)[0];
							boolean b = testitem(tmst1, tmst2, tmst3, tmst4);
							if (b) {
								String[] add_f, val = lexicalFusion(unitem.get(i), unitem.get(j));
								String[] val3 = unitem.get(k);
								String[] val4 = unitem.get(l);
								String[] add = Fusion(val, val3);
								add_f = Fusion(add, val4);
								valrtn.add(add_f);
							}
						}
					}

				}
			}
			break;

		case 10:
			for (int i = 0; i < 2; i++) {
				for (int j = 2; j < 4; j++) {
					for (int k = 4; k < 6; k++) {
						for (int l = 6; l < 8; l++) {
							for (int l2 = 8; l2 < 10; l2++) {
								String tmst1 = unitem.get(i)[0];
								String tmst2 = unitem.get(j)[0];
								String tmst3 = unitem.get(k)[0];
								String tmst4 = unitem.get(l)[0];
								String tmst5 = unitem.get(l2)[0];
								boolean b = testitem(tmst1, tmst2, tmst3, tmst4, tmst5);
								if (b) {
									String[] add_f, add_f1, val = lexicalFusion(unitem.get(i), unitem.get(j));
									String[] val3 = unitem.get(k);
									String[] val4 = unitem.get(l);
									String[] val5 = unitem.get(l2);
									String[] add = Fusion(val, val3);
									add_f = Fusion(add, val4);
									add_f1 = Fusion(add_f, val5);
									valrtn.add(add_f1);
								}
							}
						}
					}

				}
			}
			break;

		case 12:
			for (int i = 0; i < 2; i++) {
				for (int j = 2; j < 4; j++) {
					for (int k = 4; k < 6; k++) {
						for (int l = 6; l < 8; l++) {
							for (int l2 = 8; l2 < 10; l2++) {
								for (int m = 10; m < 12; m++) {
									/*
									 * String tmst1 = unitem.get(i)[0]; String
									 * tmst2 = unitem.get(j)[0]; String tmst3 =
									 * unitem.get(k)[0]; String tmst4 =
									 * unitem.get(l)[0]; String tmst5 =
									 * unitem.get(l2)[0];
									 */
									boolean b = testitem(unitem.get(i)[0], unitem.get(j)[0], unitem.get(k)[0],
											unitem.get(l)[0], unitem.get(l2)[0], unitem.get(m)[0]);
									if (b) {
										String[] add_f, add_f1, add2, val = lexicalFusion(unitem.get(i), unitem.get(j));
										/*
										 * String[] val3 = unitem.get(k);
										 * String[] val4 = unitem.get(l);
										 * String[] val5 = unitem.get(l2);
										 * String[] add = Fusion(val, val3);
										 * add_f = Fusion(add, val4); add_f1 =
										 * Fusion(add_f, val5); add2 =
										 * Fusion(add_f1, unitem.get(m));
										 */
										add2 = Fusion(Fusion(Fusion(
												Fusion(lexicalFusion(unitem.get(i), unitem.get(j)), unitem.get(k)),
												unitem.get(l)), unitem.get(l2)), unitem.get(m));

										valrtn.add(add2);
									}
								}
							}
						}
					}

				}
			}
			break;

		case 14:
			for (int i = 0; i < 2; i++) {
				for (int j = 2; j < 4; j++) {
					for (int k = 4; k < 6; k++) {
						for (int l = 6; l < 8; l++) {
							for (int l2 = 8; l2 < 10; l2++) {
								for (int m = 10; m < 12; m++) {
									for (int m2 = 12; m2 < 14; m2++) {
										boolean b = testitem(unitem.get(i)[0], unitem.get(j)[0], unitem.get(k)[0],
												unitem.get(l)[0], unitem.get(l2)[0], unitem.get(m)[0],
												unitem.get(m2)[0]);
										if (b) {
											String[] add_f;

											add_f = Fusion(Fusion(
													Fusion(Fusion(Fusion(lexicalFusion(unitem.get(i), unitem.get(j)),
															unitem.get(k)), unitem.get(l)), unitem.get(l2)),
													unitem.get(m)), unitem.get(m2));

											valrtn.add(add_f);
										}
									}

								}
							}
						}
					}

				}
			}
			break;

		case 16:
			for (int i = 0; i < 2; i++) {
				for (int j = 2; j < 4; j++) {
					for (int k = 4; k < 6; k++) {
						for (int l = 6; l < 8; l++) {
							for (int l2 = 8; l2 < 10; l2++) {
								for (int m = 10; m < 12; m++) {
									for (int m2 = 12; m2 < 14; m2++) {
										for (int n = 14; n < 16; n++) {
											boolean b = testitem(unitem.get(i)[0], unitem.get(j)[0], unitem.get(k)[0],
													unitem.get(l)[0], unitem.get(l2)[0], unitem.get(m)[0],
													unitem.get(m2)[0], unitem.get(n)[0]);
											if (b) {
												String[] add_f;

												add_f = Fusion(Fusion(Fusion(
														Fusion(Fusion(
																Fusion(lexicalFusion(unitem.get(i), unitem.get(j)),
																		unitem.get(k)),
																unitem.get(l)), unitem.get(l2)),
														unitem.get(m)), unitem.get(m2)), unitem.get(n));

												valrtn.add(add_f);
											}
										}

									}

								}
							}
						}
					}

				}
			}
			break;

		case 18:
			for (int i = 0; i < 2; i++) {
				for (int j = 2; j < 4; j++) {
					for (int k = 4; k < 6; k++) {
						for (int l = 6; l < 8; l++) {
							for (int l2 = 8; l2 < 10; l2++) {
								for (int m = 10; m < 12; m++) {
									for (int m2 = 12; m2 < 14; m2++) {
										for (int n = 14; n < 16; n++) {
											for (int n3 = 16; n3 < 18; n3++) {
												boolean b = testitem(unitem.get(i)[0], unitem.get(j)[0],
														unitem.get(k)[0], unitem.get(l)[0], unitem.get(l2)[0],
														unitem.get(m)[0], unitem.get(m2)[0], unitem.get(n)[0],
														unitem.get(n3)[0]);
												if (b) {
													String[] add_f;

													add_f = Fusion(
															Fusion(Fusion(
																	Fusion(Fusion(
																			Fusion(Fusion(
																					lexicalFusion(unitem.get(i),
																							unitem.get(j)),
																					unitem.get(k)), unitem.get(l)),
																			unitem.get(l2)), unitem.get(m)),
																	unitem.get(m2)), unitem.get(n)),
															unitem.get(n3));

													valrtn.add(add_f);
												}
											}
										}

									}

								}
							}
						}
					}

				}
			}
			break;

		case 20:
			for (int i = 0; i < 2; i++) {
				for (int j = 2; j < 4; j++) {
					for (int k = 4; k < 6; k++) {
						for (int l = 6; l < 8; l++) {
							for (int l2 = 8; l2 < 10; l2++) {
								for (int m = 10; m < 12; m++) {
									for (int m2 = 12; m2 < 14; m2++) {
										for (int n = 14; n < 16; n++) {
											for (int n3 = 16; n3 < 18; n3++) {
												for (int o = 18; o < 20; o++) {
													boolean b = testitem(unitem.get(i)[0], unitem.get(j)[0],
															unitem.get(k)[0], unitem.get(l)[0], unitem.get(l2)[0],
															unitem.get(m)[0], unitem.get(m2)[0], unitem.get(n)[0],
															unitem.get(n3)[0], unitem.get(o)[0]);
													if (b) {
														String[] add_f, add_fnxt;

														add_f = Fusion(
																Fusion(Fusion(
																		Fusion(Fusion(
																				Fusion(Fusion(
																						lexicalFusion(unitem.get(i),
																								unitem.get(j)),
																						unitem.get(k)), unitem.get(l)),
																				unitem.get(l2)), unitem.get(m)),
																		unitem.get(m2)), unitem.get(n)),
																unitem.get(n3));
														add_fnxt = Fusion(add_f, unitem.get(o));

														valrtn.add(add_fnxt);
													}
												}
											}
										}

									}

								}
							}
						}
					}

				}
			}
			break;

		case 22:
			for (int i = 0; i < 2; i++) {
				for (int j = 2; j < 4; j++) {
					for (int k = 4; k < 6; k++) {
						for (int l = 6; l < 8; l++) {
							for (int l2 = 8; l2 < 10; l2++) {
								for (int m = 10; m < 12; m++) {
									for (int m2 = 12; m2 < 14; m2++) {
										for (int n = 14; n < 16; n++) {
											for (int n3 = 16; n3 < 18; n3++) {
												for (int o = 18; o < 20; o++) {
													for (int o2 = 20; o2 < 22; o2++) {
														boolean b = testitem(unitem.get(i)[0], unitem.get(j)[0],
																unitem.get(k)[0], unitem.get(l)[0], unitem.get(l2)[0],
																unitem.get(m)[0], unitem.get(m2)[0], unitem.get(n)[0],
																unitem.get(n3)[0], unitem.get(o)[0], unitem.get(o2)[0]);
														if (b) {
															String[] add_f, add_fnxt;

															add_f = Fusion(
																	Fusion(Fusion(
																			Fusion(Fusion(
																					Fusion(Fusion(
																							lexicalFusion(unitem.get(i),
																									unitem.get(j)),
																							unitem.get(k)),
																							unitem.get(l)),
																					unitem.get(l2)), unitem.get(m)),
																			unitem.get(m2)), unitem.get(n)),
																	unitem.get(n3));
															add_fnxt = Fusion(Fusion(add_f, unitem.get(o)),
																	unitem.get(o2));

															valrtn.add(add_fnxt);
														}
													}
												}
											}
										}

									}

								}
							}
						}
					}

				}
			}
			break;

		default:
			break;
		}
		return valrtn;

	}

	public boolean testitem(String... listItem) {
		int taille = listItem.length;
		boolean b = true;
		String[] tabitem = new String[taille];
		for (int i = 0; i < taille; i++) {
			tabitem[i] = listItem[i];
		}
		for (int i = 0; i < tabitem.length; i++) {
			for (int j = i + 1; j < tabitem.length; j++) {
				b = b && !(tabitem[i] == tabitem[j]);
			}
		}
		return b;

	}

	private String[] Fusion(String[] val, String[] val3) {
		int j = 0, p = val.length, q = val3.length;
		String[] r = new String[p + q];
		for (j = 0; j < p; j++) {
			r[j] = val[j];
		}
		for (int i = 0; i < val3.length; i++) {
			r[j + i] = val3[i];
		}
		return r;
	}

	public boolean lexicalComparaison(String[] pattern1, String[] pattern2) {

		// case of 1-gradualItemset
		if (pattern1.length == pattern2.length && pattern1.length == 2) {
			if (pattern1[0] == pattern2[0])
				return false;
			return true;
		}
		// case pattern of size 2
		if (pattern1.length != pattern2.length) {
			return false;
		} else {
			int i;
			for (i = 0; i < pattern2.length - 3; i++) {
				if (pattern1[i] == pattern2[i]) {
					continue;
				} else {
					break;
				}
			}
			if (i == pattern2.length - 3 && pattern1[pattern1.length - 2] != pattern2[pattern1.length - 2]) {
				return true;
			} else {
				return false;
			}
		}
	}

	public String[] lexicalFusion(String[] pattern1, String[] pattern2) {
		String[] fusion = null;
		int i;
		if (lexicalComparaison(pattern1, pattern2)) {
			fusion = new String[pattern1.length + 2];
			// fusion = pattern1 +pattern2;
			for (i = 0; i < pattern1.length; i++) {

				fusion[i] = pattern1[i];
			}
			fusion[pattern1.length] = pattern2[pattern1.length - 2];
			fusion[pattern1.length + 1] = pattern2[pattern1.length - 1];
			return fusion;
		}
		return fusion;// null value
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] attr = new String[2];
		attr[0] = "A";
		attr[1] = "-";
		String[] attr1 = new String[2];
		attr1[0] = "A";
		attr1[1] = "+";

		String[] attr3 = new String[2];
		attr3[0] = "B";
		attr3[1] = "-";
		String[] attr4 = new String[2];
		attr4[0] = "B";
		attr4[1] = "+";
		String[] attr5 = new String[2];
		attr5[0] = "c";
		attr5[1] = "-";
		String[] attr6 = new String[2];
		attr6[0] = "c";
		attr6[1] = "+";
		String[] attr7 = new String[2];
		attr7[0] = "D";
		attr7[1] = "-";
		String[] attr8 = new String[2];
		attr8[0] = "D";
		attr8[1] = "+";
		String[] attr9 = new String[2];
		attr9[0] = "E";
		attr9[1] = "-";
		String[] attr10 = new String[2];
		attr10[0] = "E";
		attr10[1] = "+";
		String[] attr11 = new String[2];
		attr11[0] = "F";
		attr11[1] = "-";
		String[] attr12 = new String[2];
		attr12[0] = "F";
		attr12[1] = "+";
		String[] attr13 = new String[2];
		attr13[0] = "G";
		attr13[1] = "-";
		String[] attr14 = new String[2];
		attr14[0] = "G";
		attr14[1] = "+";
		ArrayList<String[]> t = new ArrayList<>();
		t.add(attr);
		t.add(attr1);
		t.add(attr3);
		t.add(attr4);
		t.add(attr5);
		t.add(attr6);
		t.add(attr7);
		t.add(attr8);
		t.add(attr9);
		t.add(attr10);
		t.add(attr11);
		t.add(attr12);
		t.add(attr13);
		t.add(attr14);
		main m = new main();
		t = m.genIndexUsed(t, t);
		Tools t1 = new Tools();
		for (Iterator iterator = t.iterator(); iterator.hasNext();) {
			String[] strings = (String[]) iterator.next();
			System.out.println(t1.printGrad_Itemset(strings));

		}
		String tmst = attr.toString();
		System.out.println(attr.length);
		System.out.println(tmst.length());
		for (int i = 0; i < attr.length; i++) {
			// System.out.println(attr[i] + " ,");
		}
		for (int i = 0; i < tmst.length(); i++) {
			System.out.println(tmst.charAt(i) + " ,");
		}
		tmst = tmst.substring(0, (tmst.length() - 2));
		// System.out.println(tmst.length());
		for (int i = 0; i < tmst.length(); i++) {
			// System.out.println(tmst.charAt(i) + " ,");
		}
	}

}
