function TestPackageListResponseDTO(testPackageListResp) {
	this.testPackages=testPackages;
	this.count=testPackageListResp.count;
	this.error=testPackageListResp.error;
	this.success=testPackageListResp.success;
}

TestPackageListResponseDTO.getTestPackage = function() {
	return this.testPackages;
}

TestPackageListResponseDTO.getCount = function() {
	return this.count;
}

TestPackageListResponseDTO.getError= function() {
	return this.error;
}

TestPackageListResponseDTO.getSuccess= function() {
	return this.success;
}