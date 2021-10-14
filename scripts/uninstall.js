module.exports = function (context) {
  const fs = require('fs')
  const path = require('path')
  const configXml = path.join(context.opts.projectRoot, 'config.xml')
  const et = require('elementtree')

  const data = fs.readFileSync(configXml).toString()
  const etree = et.parse(data)
  const packageId = etree.getroot().attrib.id || etree.getroot().attrib['android-packageName']
  console.log('packageId', packageId)

  let mainContent = fs.readFileSync(path.join(context.opts.projectRoot, 'plugins/@huangang/cordova-plugin-webview-x5/platforms/android/template/OriginMainActivity.java'), 'utf8')
  mainContent = mainContent.replace('<packageName>', packageId)
  fs.writeFileSync(path.join(
    context.opts.projectRoot,
    'platforms/android/app/src/main/java/',
    packageId.replace(/\./g, '/'),
    '/MainActivity.java'), mainContent)
}
