/**
 * Copyright 2017, FMR LLC.
 * All Rights Reserved.
 * Fidelity Confidential Information
 */
package net.stn;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author a365143
 *
 */
public class TokenTest {
	public static void main(String[] args) {
		String[] tokenPartyIdentityAllSites = new String[] {
				// MMK311
				"AP109573,AP102563,AP107296,AP111951,AP111095,AP114738,AP106532,AP109836,AP112534,AP110358,AP011763,AP110733",
				// MMK321
				"AP109573,AP102563,AP107296,AP111951,AP111095,AP114738,AP106532,AP109836,AP112534,AP110358,AP011763,AP110733",
				// OMA111
				"AP109573,AP102563,AP107296,AP111951,AP111095,AP114738,AP106532,AP109836,AP112534,AP110358,AP011763,AP110733",
				// OMA112
				"AP109573,AP102563,AP107296,AP111951,AP111095,AP114738,AP106532,AP109836,AP112534,AP110358,AP011763,AP110733",
				// OMA121
				"AP109573,AP102563,AP107296,AP111951,AP111095,AP114738,AP106532,AP109836,AP112534,AP110358,AP011763,AP110733",
				// OMA122
				"AP109573,AP102563,AP107296,AP111951,AP111095,AP114738,AP106532,AP109836,AP112534,AP110358,AP011763,AP110733",
				// RTP211
				"AP109573,AP102563,AP107296,AP111951,AP111095,AP114738,AP106532,AP109836,AP112534,AP110358,AP011763,AP110733",
				// RTP212
				"AP109573,AP102563,AP107296,AP111951,AP111095,AP114738,AP106532,AP109836,AP112534,AP110358,AP011763,AP110733"
		};

		String[] tokenCpIdentityAllSites = new String[] {
				// MMK311
				"AP109573,AP111095,AP114738,AP106532,AP109836,AP112534,AP107296,AP110358,AP110085,AP102563,AP104522,AP110085,CMBPM,AP103323,AP103220,AP105760,AP010981,AP011663,AP011544,AP110113,AP001515,AP112559,AP116348,AP011763,AP110733,AP111951",
				// MMK321
				"AP109573,AP111095,AP114738,AP106532,AP109836,AP112534,AP107296,AP110358,AP110085,AP102563,AP104522,AP110085,CMBPM,AP103323,AP103220,AP105760,AP010981,AP011663,AP011544,AP110113,AP001515,AP112559,AP116348,AP011763,AP110733,AP111951",
				// OMA111
				"AP109573,AP111095,AP114738,AP106532,AP109836,AP112534,AP107296,AP110358,AP110085,AP102563,AP104522,AP110085,CMBPM,AP103323,AP103220,AP105760,AP010981,AP011663,AP011544,AP110113,AP001515,AP112559,AP116348,AP011763,AP110733,AP111951",
				// OMA112
				"AP109573,AP111095,AP114738,AP106532,AP109836,AP112534,AP107296,AP110358,AP110085,AP102563,AP104522,AP110085,CMBPM,AP103323,AP103220,AP105760,AP010981,AP011663,AP011544,AP110113,AP001515,AP112559,AP116348,AP011763,AP110733,AP111951",
				// OMA121
				"AP109573,AP111095,AP114738,AP106532,AP109836,AP112534,AP107296,AP110358,AP110085,AP102563,AP104522,AP110085,CMBPM,AP103323,AP103220,AP105760,AP010981,AP011663,AP011544,AP110113,AP001515,AP112559,AP116348,AP011763,AP110733,AP111951",
				// OMA122
				"AP109573,AP111095,AP114738,AP106532,AP109836,AP112534,AP107296,AP110358,AP110085,AP102563,AP104522,AP110085,CMBPM,AP103323,AP103220,AP105760,AP010981,AP011663,AP011544,AP110113,AP001515,AP112559,AP116348,AP011763,AP110733,AP111951",
				// RTP211
				"AP109573,AP111095,AP114738,AP106532,AP109836,AP112534,AP107296,AP110358,AP110085,AP102563,AP104522,AP110085,CMBPM,AP103323,AP103220,AP105760,AP010981,AP011663,AP011544,AP110113,AP001515,AP112559,AP116348,AP011763,AP110733,AP111951",
				// RTP212
				"AP109573,AP111095,AP114738,AP106532,AP109836,AP112534,AP107296,AP110358,AP110085,AP102563,AP104522,AP110085,CMBPM,AP103323,AP103220,AP105760,AP010981,AP011663,AP011544,AP110113,AP001515,AP112559,AP116348,AP011763,AP110733,AP111951"
		};

		Assert.isTrue(tokenPartyIdentityAllSites.length == tokenCpIdentityAllSites.length);

		for (int i = 0; i < tokenPartyIdentityAllSites.length; i++) {
			String[] partyIdentityTokensPerSite = StringUtils.tokenizeToStringArray(tokenPartyIdentityAllSites[i], ",");
			for (int j = 0; j < partyIdentityTokensPerSite.length; j++) {
				if (tokenCpIdentityAllSites[i].indexOf(partyIdentityTokensPerSite[j]) == -1) {
					System.err.println("Token not present:" + partyIdentityTokensPerSite[j]);
				}
			}
		}
	}
}
