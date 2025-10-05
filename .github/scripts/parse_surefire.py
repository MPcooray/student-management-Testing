#!/usr/bin/env python3
import sys
import glob
import xml.etree.ElementTree as ET
import os

def gather_reports(paths):
    files = []
    if paths:
        for p in paths:
            # allow globs
            files.extend(glob.glob(p))
    else:
        files = glob.glob('target/surefire-reports/*.xml')
    return sorted(set(files))

def parse(files):
    cases = []
    total = fail = err = 0
    for f in files:
        try:
            tree = ET.parse(f)
            root = tree.getroot()
            # surefire reports may have testsuite or testsuites
            for tc in root.findall('.//testcase'):
                name = tc.attrib.get('name','')
                classname = tc.attrib.get('classname','')
                time = tc.attrib.get('time','')
                status = 'PASS'
                if tc.find('failure') is not None:
                    status = 'FAIL'
                    fail += 1
                elif tc.find('error') is not None:
                    status = 'ERROR'
                    err += 1
                total += 1
                cases.append((classname, name, status, time))
        except Exception:
            # ignore parse errors
            continue
    return total, fail, err, cases

def make_md(total, fail, err, cases, artifact_note):
    lines = []
    lines.append('## Test results')
    lines.append('')
    lines.append(f'- Total tests: **{total}**')
    lines.append(f'- Failures: **{fail}**')
    lines.append(f'- Errors: **{err}**')
    lines.append('')
    if cases:
        lines.append('| Class | Test | Result | Time |')
        lines.append('|---|---|---|---:|')
        for classname, name, status, time in cases:
            lines.append('| `%s` | `%s` | **%s** | %s |' % (classname, name, status, time))
        lines.append('')
    if artifact_note:
        lines.append(artifact_note)
    else:
        lines.append('No surefire report files found.')
    return '\n'.join(lines)

def main():
    out_path = None
    args = sys.argv[1:]
    if args:
        out_path = args[0]
        report_paths = args[1:]
    else:
        report_paths = []

    files = gather_reports(report_paths)
    total, fail, err, cases = parse(files)
    artifact_note = ''
    if files:
        artifact_note = 'Artifacts: surefire reports uploaded as `backend-test-reports` artifact.'

    md = make_md(total, fail, err, cases, artifact_note)

    if out_path:
        try:
            with open(out_path, 'a', encoding='utf-8') as fh:
                fh.write('\n')
                fh.write(md)
        except Exception:
            print(md)
    else:
        print(md)

if __name__ == '__main__':
    main()
